package ru.zimin.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.zimin.votesystem.model.Dish;
import ru.zimin.votesystem.repository.DishRepository;
import ru.zimin.votesystem.to.DishTo;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import java.util.List;

import static ru.zimin.votesystem.util.ValidationUtil.checkNew;
import static ru.zimin.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Transactional
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        dish.setRestaurant(restaurantService.get(restaurantId));
        return dishRepository.save(dish);
    }

    @Transactional
    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        Dish loadedDish = dishRepository.get(dish.getId(), restaurantId);
        checkNotFoundWithId(loadedDish, dish.getId());

        dish.setRestaurant(loadedDish.getRestaurant());
        dishRepository.save(dish);
    }

    public List<DishTo> getAll() {
        return ToUtil.dishesAsToList(dishRepository.getAll());
    }

    public List<Dish> getAll(int restaurantId) {
        return dishRepository.getAllByRestaurantId(restaurantId);
    }

    public Dish get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(dishRepository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(dishRepository.delete(id, restaurantId) != 0, id);
    }
}