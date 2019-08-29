package ru.zimin.votesystem.util;

import ru.zimin.votesystem.model.Dish;
import ru.zimin.votesystem.model.Menu;
import ru.zimin.votesystem.model.Restaurant;
import ru.zimin.votesystem.model.Vote;
import ru.zimin.votesystem.to.DishTo;
import ru.zimin.votesystem.to.MenuTo;
import ru.zimin.votesystem.to.RestaurantTo;
import ru.zimin.votesystem.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

public class ToUtil {

    public static List<RestaurantTo> restaurantsAsToList(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantTo::new)
                .collect(Collectors.toList());
    }

    public static List<DishTo> dishesAsToList(List<Dish> dishes) {
        return dishes.stream()
                .map(DishTo::new)
                .collect(Collectors.toList());
    }

    public static List<MenuTo> menusAsToList(List<Menu> menus) {
        return menus.stream()
                .map(MenuTo::new)
                .collect(Collectors.toList());
    }

    public static List<VoteTo> votesAsToList(List<Vote> votes) {
        return votes.stream()
                .map(VoteTo::new)
                .collect(Collectors.toList());
    }
}
