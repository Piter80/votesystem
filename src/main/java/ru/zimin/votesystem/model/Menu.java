package ru.zimin.votesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    @Column(nullable = false)
    LocalDate menuDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Dish dish;

    @Column(nullable = false)
    @NotNull
    @Min(1)
    Integer price;

    public Menu() {
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getMenuDate(), menu.getRestaurant(), menu.getDish(), menu.getPrice());
    }

    public Menu(Integer id, LocalDate menuDate, Integer price) {
        super(id);
        setMenuDate(menuDate);
        this.price = price;
    }

    public Menu(Integer id, LocalDate menuDate, Restaurant restaurant, Dish dish, Integer price) {
        super(id);
        setMenuDate(menuDate);
        this.restaurant = restaurant;
        this.dish = dish;
        this.price = price;
    }

    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDate date) {
        if (date == null) date = LocalDate.now();
        this.menuDate = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
