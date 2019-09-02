package ru.zimin.votesystem.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zimin.votesystem.View;
import ru.zimin.votesystem.model.Dish;
import ru.zimin.votesystem.model.Menu;
import ru.zimin.votesystem.model.Restaurant;
import ru.zimin.votesystem.to.DishTo;
import ru.zimin.votesystem.to.MenuTo;
import ru.zimin.votesystem.to.RestaurantTo;
import ru.zimin.votesystem.to.VoteTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController extends AbstractRestaurantController {

    public static final String REST_URL = "/admin/restaurants";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
        super.update(restaurant);
    }

    @Override
    @GetMapping
    public List<RestaurantTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/{id}/for")
    public Restaurant getForDay(@PathVariable int id, @RequestParam LocalDate day) {
        return super.getForDay(id, day);
    }

    @Override
    @GetMapping("/for")
    public List<Restaurant> getAllForDay(@RequestParam LocalDate day) {
        return super.getAllForDay(day);
    }

    //dishes
    @Override
    @PostMapping(value = "/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish createDish(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int restaurantId) {
        return super.createDish(dish, restaurantId);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDish(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int restaurantId) {
        super.updateDish(dish, restaurantId);
    }

    @Override
    @GetMapping("/{restaurantId}/dishes")
    public List<Dish> getAllDishesForRestaurant(@PathVariable int restaurantId) {
        return super.getAllDishesForRestaurant(restaurantId);
    }

    @Override
    @GetMapping("/dishes")
    public List<DishTo> getAllDishes() {
        return super.getAllDishes();
    }

    @Override
    @GetMapping("/{restaurantId}/dishes/{id}")
    public Dish getDish(@PathVariable int id, @PathVariable int restaurantId) {
        return super.getDish(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{restaurantId}/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable int id, @PathVariable int restaurantId) {
        super.deleteDish(id, restaurantId);
    }

    //menus
    @Override
    @PostMapping(value = "/{restaurantId}/menus/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu createMenu(@Validated(View.Web.class) @RequestBody Menu menu,
                              @PathVariable int restaurantId,
                              @PathVariable int dishId) {
        return super.createMenu(menu, restaurantId, dishId);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/menus/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMenu(@Validated(View.Web.class) @RequestBody Menu menu,
                              @PathVariable int restaurantId,
                              @PathVariable int dishId) {
        super.updateMenu(menu, restaurantId, dishId);
    }

    @Override
    @GetMapping("/{restaurantId}/menus")
    public List<Menu> getAllMenusForRestaurant(@PathVariable int restaurantId) {
        return super.getAllMenusForRestaurant(restaurantId);
    }

    @Override
    @GetMapping("/menus")
    public List<MenuTo> getAllMenus() {
        return super.getAllMenus();
    }

    @Override
    @GetMapping("/{restaurantId}/menus/for")
    public List<Menu> getAllMenusForDayByRestaurantId(@PathVariable int restaurantId, @RequestParam LocalDate day) {
        return super.getAllMenusForDayByRestaurantId(restaurantId, day);
    }

    @Override
    @GetMapping("/menus/for")
    public List<Menu> getAllMenusForDay(@RequestParam LocalDate day) {
        return super.getAllMenusForDay(day);
    }

    @Override
    @GetMapping("/{restaurantId}/menus/{id}")
    public Menu getMenu(@PathVariable int id, @PathVariable int restaurantId) {
        return super.getMenu(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{restaurantId}/menus/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable int id, @PathVariable int restaurantId) {
        super.deleteMenu(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{restaurantId}/menus/for")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllMenusForDay(@PathVariable int restaurantId, @RequestParam LocalDate day) {
        super.deleteAllMenusForDay(restaurantId, day);
    }

    //votes
    @Override
    @GetMapping("/{restaurantId}/votes/for")
    public List<VoteTo> getAllForDateForRestaurant(@RequestParam LocalDate day, @PathVariable int restaurantId) {
        return super.getAllForDateForRestaurant(day, restaurantId);
    }

    @GetMapping("/{restaurantId}/votes")
    public List<VoteTo> getAllForTodayForRestaurant(@PathVariable int restaurantId) {
        return super.getAllForDateForRestaurant(null, restaurantId);
    }
}
