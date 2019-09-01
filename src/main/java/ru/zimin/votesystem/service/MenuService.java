package ru.zimin.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.zimin.votesystem.model.Menu;
import ru.zimin.votesystem.repository.MenuRepository;
import ru.zimin.votesystem.repository.RestaurantRepository;
import ru.zimin.votesystem.to.MenuTo;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.zimin.votesystem.util.ValidationUtil.checkNew;
import static ru.zimin.votesystem.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishService dishService;

    @Transactional
    public Menu create(Menu menu, int restaurantId, int dishId) {
        Assert.notNull(menu, "menu must not be null");
        checkNew(menu);
        setDate(menu, restaurantId, dishId);
        return menuRepository.save(menu);
    }

    private void setDate(Menu menu, int restaurantId, int dishId) {
        if (menu.getMenuDate() == null) {
            menu.setMenuDate(LocalDate.now());
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        menu.setDish(dishService.get(dishId, restaurantId));
    }

    @Transactional
    public void update(Menu menu, int restaurantId, int dishId) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(menuRepository.get(menu.getId(), restaurantId), menu.getId());
        setDate(menu, restaurantId, dishId);
        menuRepository.save(menu);
    }

    public List<MenuTo> getAll() {
        return ToUtil.menusAsToList(menuRepository.getAll());
    }

    public List<Menu> getAllForRestaurantId(int restaurantId) {
        return menuRepository.getAllByRestaurantId(restaurantId);
    }

    public List<Menu> getAllForDayByRestaurantId(int restaurantId, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return menuRepository.getAllForDateAndRestaurantId(restaurantId, date);
    }

    public List<Menu> getAllForDay(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return menuRepository.getAllForDate(date);
    }

    public Menu get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id, restaurantId) != 0, id);
    }

    public void deleteAllForDay(int restaurantId, LocalDate date) {
        checkNotFoundWithId(menuRepository.deleteAll(restaurantId, date) != 0, restaurantId);
    }
}