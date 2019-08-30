package ru.zimin.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zimin.votesystem.model.AbstractBaseEntity;
import ru.zimin.votesystem.model.Restaurant;
import ru.zimin.votesystem.repository.RestaurantRepository;
import ru.zimin.votesystem.to.RestaurantTo;
import ru.zimin.votesystem.util.ToUtil;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.zimin.votesystem.util.ValidationUtil.checkNew;
import static ru.zimin.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    public void update (Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurant, restaurant.getId());
        repository.save(restaurant);
    }

    public List<RestaurantTo> getAll() {
        return ToUtil.restaurantsAsToList(repository.getAll());
    }

    public Restaurant get (int id) throws NotFoundException {
        Restaurant restaurant = repository.getFullById(id);
        if (restaurant == null) throw new NotFoundException("Restaurant not found");
        return checkNotFoundWithId(restaurant, id);
    }

    public void delete (int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public Restaurant getForDay(int id, LocalDate date) throws NotFoundException {
        return checkNotFoundWithId(repository.getByIdForDay(id, Objects.requireNonNullElseGet(date, LocalDate::now)), id);
    }

    public List<Restaurant> getAllForDay(LocalDate localDate) {
        return repository.getAllForDay(Objects.requireNonNullElseGet(localDate, LocalDate::now));
    }

    public List<Restaurant> getAllForToday() {
        return repository.getAllForDay(LocalDate.now());
    }
}
