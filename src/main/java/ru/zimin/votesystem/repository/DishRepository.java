package ru.zimin.votesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zimin.votesystem.model.Dish;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Transactional
    @Override
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d")
    List<Dish> getAll();

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> getAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query("SELECT DISTINCT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Dish get(@Param("id") int id, @Param("restaurantId") int restaurantId);
}
