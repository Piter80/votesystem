package ru.zimin.votesystem.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zimin.votesystem.model.Dish;
import ru.zimin.votesystem.model.Menu;
import ru.zimin.votesystem.model.Restaurant;
import ru.zimin.votesystem.model.Vote;
import ru.zimin.votesystem.service.DishService;
import ru.zimin.votesystem.service.MenuService;
import ru.zimin.votesystem.service.RestaurantService;
import ru.zimin.votesystem.service.VoteService;
import ru.zimin.votesystem.to.DishTo;
import ru.zimin.votesystem.to.MenuTo;
import ru.zimin.votesystem.to.RestaurantTo;
import ru.zimin.votesystem.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private VoteService voteService;

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant with name={}", restaurant.getName());
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant) {
        log.info("update restaurant with id={}", restaurant.getId());
        restaurantService.update(restaurant);
    }

    public List<RestaurantTo> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    public Restaurant get(int id) {
        log.info("get restaurant with id={}", id);
        return restaurantService.get(id);
    }

    public void delete(int id) {
        log.info("delete restaurant with id={}", id);
        restaurantService.delete(id);
    }

    public Restaurant getForDay(int id, LocalDate localDate) {
        log.info("get restaurant with id={} for day", id);
        return restaurantService.getForDay(id, localDate);
    }

    public List<Restaurant> getAllForDay(LocalDate localDate) {
        log.info("get all restaurants for day");
        return restaurantService.getAllForDay(localDate);
    }

    public List<Restaurant> getAllForToday() {
        log.info("get all restaurants for today");
        return restaurantService.getAllForToday();
    }

    //vote
    public Vote vote(LocalDate date, int userId, int restaurantId, LocalTime time) {
        log.info("user with id={} voted for restaurant with id={}", userId, restaurantId);
        return voteService.vote(date, userId, restaurantId, time);
    }

    //votes
    public List<VoteTo> getAllForDateForUser(LocalDate date, int userId) {
        log.info("user with id={} get all his votes for current date", userId);
        return voteService.getAllForDateForUser(date, userId);
    }

    public List<VoteTo> getAllForDateForRestaurant(LocalDate date, int restaurantId) {
        log.info("get all votes for date for restaurant with id={}", restaurantId);
        return voteService.getAllForDateForRestaurant(date, restaurantId);
    }

    //dishes
    public Dish createDish(Dish dish, int restaurantId) {
        log.info("create dish with name={} for restaurant with id={}", dish.getName(), restaurantId);
        return dishService.create(dish, restaurantId);
    }

    public void updateDish(Dish dish, int restaurantId) {
        log.info("update dish with id={} for restaurant with id={}", dish.getId(), restaurantId);
        dishService.update(dish, restaurantId);
    }

    public List<Dish> getAllDishesForRestaurant(int restaurantId) {
        log.info("get all dishes for restaurant with id={}", restaurantId);
        return dishService.getAll(restaurantId);
    }

    public List<DishTo> getAllDishes() {
        log.info("get all dishes");
        return dishService.getAll();
    }

    public Dish getDish(int id, int restaurantId) {
        log.info("get dish with id={} for restaurant with id={}", id, restaurantId);
        return dishService.get(id, restaurantId);
    }

    public void deleteDish(int id, int restaurantId) {
        log.info("delete dish with id={} for restaurant with id={}", id, restaurantId);
        dishService.delete(id, restaurantId);
    }

    //menus
    public Menu createMenu(Menu menu, int restaurantId, int dishId) {
        log.info("create menu for restaurant with id={} and dish with id={}", restaurantId, dishId);
        return menuService.create(menu, restaurantId, dishId);
    }

    public void updateMenu(Menu menu, int restaurantId, int dishId) {
        log.info("update menu with id={} for restaurant with id={} and dish with id={}", menu.getId(), restaurantId, dishId);
        menuService.update(menu, restaurantId, dishId);
    }

    public List<Menu> getAllMenusForRestaurant(int restaurantId) {
        log.info("get all menus for restaurant with id={}", restaurantId);
        return menuService.getAllForRestaurantId(restaurantId);
    }

    public List<MenuTo> getAllMenus() {
        log.info("get all menus");
        return menuService.getAll();
    }

    public List<Menu> getAllMenusForDayByRestaurantId(int restaurantId, LocalDate date) {
        log.info("get all menus for restaurant with id={} for day", restaurantId);
        return menuService.getAllForDayByRestaurantId(restaurantId, date);
    }

    public List<Menu> getAllMenusForDay(LocalDate date) {
        log.info("get all menus for day");
        return menuService.getAllForDay(date);
    }

    public Menu getMenu(int id, int restaurantId) {
        log.info("get menu with id={} for restaurant with id={}", id, restaurantId);
        return menuService.get(id, restaurantId);
    }

    public void deleteMenu(int id, int restaurantId) {
        log.info("delete menu with id={} for restaurant with id={}", id, restaurantId);
        menuService.delete(id, restaurantId);
    }

    public void deleteAllMenusForDay(int restaurantId, LocalDate date) {
        log.info("delete all menus for restaurant with id={}", restaurantId);
        menuService.deleteAllForDay(restaurantId, date);
    }
}
