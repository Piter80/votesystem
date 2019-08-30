package ru.zimin.votesystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.zimin.votesystem.model.Restaurant;
import ru.zimin.votesystem.to.RestaurantTo;
import ru.zimin.votesystem.util.JpaUtil;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.zimin.votesystem.data.TestData.*;


class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "new one");
        Restaurant created = restaurantService.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(newRestaurant, created);
    }

    @Test
    void duplicateNameCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                restaurantService.create(new Restaurant(null, "Eat and Die")));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = new Restaurant(RES2);
        updated.setName("updated");
        restaurantService.update(new Restaurant(updated));
        assertMatch(updated, restaurantService.get(RES2_ID), "menus");
    }

    @Test
    void delete() throws Exception {
        assertMatch(restaurantService.getAllForDay(LocalDate.of(2019, 7, 1)), RESTAURANTS_FOR_DAY_20190701);
        restaurantService.delete(RES1_ID);
        assertMatch(restaurantService.getAllForDay(LocalDate.of(2019, 7, 1)), RESTAURANTS_FOR_DAY_20190701_WITHOUT_RES1);
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.delete(1));
    }

    @Test
    void get() throws Exception {
        Restaurant restaurant = restaurantService.get(RES1_ID);
        assertMatch(restaurant, RES1, "menus");
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.get(95));
    }

    @Test
    void getAll() throws Exception {
        List<RestaurantTo> all = restaurantService.getAll();
        assertMatch(all, ToUtil.restaurantsAsToList(RESTAURANTS));
    }

    @Test
    void getForDay() throws Exception {
        Restaurant restaurant = restaurantService.getForDay(RES3_ID, LocalDate.parse("2019-07-02"));
        assertMatch(restaurant, RES3, "menus");
    }

    @Test
    void getForDayNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.getForDay(RES3_ID, LocalDate.parse("2019-07-05")));
    }

    @Test
    void getAllForDay() throws Exception {
        List<Restaurant> restaurants = restaurantService.getAllForDay(LocalDate.parse("2019-07-01"));
        assertMatch(restaurants, RES1, RES2, RES3);
    }

    @Test
    void getAllForEmptyDay() throws Exception {
        List<Restaurant> restaurants = restaurantService.getAllForDay(LocalDate.parse("2019-07-05"));
        assertMatch(restaurants, List.of());
    }
}
