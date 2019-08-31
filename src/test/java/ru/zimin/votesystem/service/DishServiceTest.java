package ru.zimin.votesystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.zimin.votesystem.model.Dish;
import ru.zimin.votesystem.to.DishTo;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.util.exceptions.IllegalRequestDataException;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.zimin.votesystem.data.TestData.*;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    DishService dishService;

    @Test
    void create() throws Exception {
        Dish newDish = new Dish(null, "Жранинка", RES2);
        Dish created = dishService.create(newDish, RES2_ID);
        newDish.setId(created.getId());
        assertMatch(newDish, created);
    }

    @Test
    void createNotNew() throws Exception {
        Dish notNewDish = new Dish(DISH5);
        assertThrows(IllegalRequestDataException.class, () ->
                dishService.create(notNewDish, RES2_ID));
    }

    @Test
    void duplicateCreate() throws Exception {
        Dish newDish = new Dish(DISH9);
        newDish.setId(null);
        assertThrows(DataAccessException.class, () ->
                dishService.create(newDish, RES3_ID));
    }

    @Test
    void update() throws Exception {
        Dish updated = new Dish(DISH7);
        updated.setName("duplicate dish");
        dishService.update(new Dish(updated), RES3_ID);
        assertMatch(updated, dishService.get(DISH7_ID, RES3_ID), "menus");
    }

    @Test
    void updateNotRestaurantDish() throws Exception {
        Dish updated = new Dish(DISH2);
        updated.setName("duplicate dish");
        assertThrows(NotFoundException.class, () ->
                dishService.update(new Dish(updated), RES3_ID));
    }

    @Test
    void delete() throws Exception {
        dishService.delete(DISH1_ID, RES1_ID);
        List<Dish> dishes = dishService.getAll(RES1_ID);
        assertFalse(dishes.contains(DISH1));
    }

    @Test
    void deleteNotRestaurantDish() throws Exception {
        assertThrows(NotFoundException.class, () ->
                dishService.delete(DISH1_ID,RES2_ID));
    }

    @Test
    void get() throws Exception {
        Dish dish = dishService.get(DISH4_ID, RES2_ID);
        assertMatch(dish, DISH4, "menus");
    }

    @Test
    void getNotRestaurantsDish() throws Exception {
        assertThrows(NotFoundException.class, () ->
                dishService.get(DISH4_ID, RES1_ID));
    }

    @Test
    void getAllForRestaurant() throws Exception {
        List<Dish> dishes = dishService.getAll(RES2_ID);
        assertMatch(dishes, DISH4, DISH5, DISH6);
    }

    @Test
    void getAll() throws Exception {
        List<DishTo> all = dishService.getAll();
        assertMatch(all, ToUtil.dishesAsToList(DISHES));
    }
}
