package ru.zimin.votesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zimin.votesystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.restaurant.id=:id AND m.menuDate=:date")
    int deleteAll(@Param("id") int restaurantId, @Param("date") LocalDate date);

    @Transactional
    @Override
    Menu save(Menu menu);

    @Query("SELECT m FROM Menu m")
    List<Menu> getAll();

    @Query("SELECT DISTINCT m FROM Menu m JOIN FETCH m.dish WHERE m.restaurant.id=:restaurantId AND m.menuDate=:date")
    List<Menu> getAllForDateAndRestaurantId(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Query("SELECT DISTINCT m FROM Menu m JOIN FETCH m.dish WHERE m.menuDate=:date")
    List<Menu> getAllForDate(@Param("date") LocalDate date);

    @Query("SELECT DISTINCT m FROM Menu m JOIN FETCH m.dish WHERE m.restaurant.id=:restaurantId")
    List<Menu> getAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query("SELECT DISTINCT m FROM Menu m JOIN FETCH m.dish JOIN FETCH m.restaurant WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    Menu get(@Param("id") int id, @Param("restaurantId") int restaurantId);
}
