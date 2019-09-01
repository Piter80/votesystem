package ru.zimin.votesystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.zimin.votesystem.model.Menu;
import ru.zimin.votesystem.to.MenuTo;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.zimin.votesystem.data.TestData.*;


public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    void create() throws Exception {
        Menu newMenu = new Menu(null, LocalDate.now(), 555);
        Menu created = menuService.create(newMenu, RES1_ID, DISH2_ID);
        newMenu.setId(created.getId());
        assertMatch(newMenu, created);
    }

    @Test
    void duplicateCreate() throws Exception {
        Menu newMenu = new Menu(MENU5);
        newMenu.setId(null);
        assertThrows(DataAccessException.class, () ->
                menuService.create(newMenu, RES3_ID, DISH7_ID));
    }

    @Test
    void update() throws Exception {
        Menu updated = new Menu(MENU7);
        updated.setMenuDate(LocalDate.now());
        menuService.update(new Menu(updated), RES1_ID, DISH1_ID);
        assertMatch(updated, menuService.get(MENU7_ID, RES1_ID));
    }

    @Test
    void updateWithAlreadyExistingMenuInThisDay() throws Exception {
        Menu updated = new Menu(MENU1);
        assertThrows(DataAccessException.class, () ->
                menuService.update(new Menu(updated), RES1_ID, DISH2_ID));
    }

    @Test
    void delete() throws Exception {
        menuService.delete(MENU10_ID, RES2_ID);
        List<Menu> menus = menuService.getAllForDayByRestaurantId(RES2_ID, LocalDate.of(2019, 7, 1));
        assertFalse(menus.contains(MENU10));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                menuService.delete(1, RES3_ID));
    }

    @Test
    void get() throws Exception {
        Menu menu = menuService.get(MENU12_ID, RES3_ID);
        assertMatch(menu, MENU12);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                menuService.get(25, RES2_ID));
    }

    @Test
    void deleteAll() throws Exception {
        menuService.deleteAllForDay(RES2_ID, LocalDate.of(2019, 7, 1));
        List<Menu> allForDay = menuService.getAllForDayByRestaurantId(RES2_ID, LocalDate.of(2019, 7, 1));
        assertTrue(allForDay.isEmpty());
    }

    @Test
    void getAllForDayByRestaurantId() throws Exception {
        List<Menu> allForDay = menuService.getAllForDayByRestaurantId(RES2_ID, LocalDate.of(2019, 7, 2));
        assertMatch(allForDay, List.of(MENU9, MENU10));
    }

    @Test
    void getAllForDay() throws Exception {
        List<Menu> allForDay = menuService.getAllForDay(LocalDate.of(2019, 7, 2));
        assertMatch(allForDay, MENUS_FOR_20190702);
    }

    @Test
    void getAllForRestaurant() throws Exception {
        List<Menu> allMenus = menuService.getAllForRestaurantId(RES2_ID);
        assertMatch(allMenus, List.of(MENU4, MENU9, MENU10));
    }

    @Test
    void getAll() throws Exception {
        List<MenuTo> all = menuService.getAll();
        assertMatch(all, ToUtil.menusAsToList(MENUS));
    }
}