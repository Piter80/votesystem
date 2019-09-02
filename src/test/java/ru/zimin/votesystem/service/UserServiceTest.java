package ru.zimin.votesystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.zimin.votesystem.model.Role;
import ru.zimin.votesystem.model.User;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.zimin.votesystem.data.TestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void create() throws Exception {
        User newUser = new User(null, "NewUserTest", "newuser@ya.ru", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = userService.create(new User(newUser));
        newUser.setId(created.getId());
        assertMatch(newUser, created, "password", "registered");
    }

    @Test
    void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                userService.create(new User(null, "DuplicateMailTest", "user1@yandex.ru", "newPass", Role.ROLE_USER)));
    }

    @Test
    void delete() throws Exception {
        userService.delete(USER3_ID);
        assertMatch(userService.getAll(), ADMIN, USER1, USER2);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                userService.delete(1));
    }

    @Test
    void get() throws Exception {
        User user = userService.get(ADMIN_ID);
        assertMatch(user, ADMIN, "password", "registered");
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                userService.get(1));
    }

    @Test
    void getByEmail() throws Exception {
        User user = userService.getByEmail("user2@yandex.ru");
        assertMatch(user, USER2, "password", "registered");
    }

    @Test
    void update() throws Exception {
        User updated = new User(USER3);
        updated.setName("UpdatedTest");
        updated.setRoles(Collections.singletonList(Role.ROLE_USER));
        userService.update(new User(updated));
        assertMatch(userService.get(USER3_ID), updated, "password", "registered");
    }

    @Test
    void getAll() throws Exception {
        List<User> all = userService.getAll();
        assertMatch(all, ADMIN, USER1, USER2, USER3);
    }

    @Test
    void testValidation() throws Exception {
        validateRootCause(() -> userService.create(new User(null, "  ", "blankName@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.create(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.create(new User(null, "User", "EmptyPass@yandex.ru", "", Role.ROLE_USER)), ConstraintViolationException.class);
    }
}
