package ru.zimin.votesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zimin.votesystem.model.Restaurant;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Override
    Restaurant save(Restaurant restaurant);

    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menus m JOIN FETCH m.dish WHERE r.id=:restaurantId")
    Restaurant getFullById(@Param("restaurantId") Integer restaurantId);

    @Override
    Optional<Restaurant> findById(Integer id);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.menus m JOIN FETCH m.dish WHERE m.menuDate=:day ORDER BY r.id")
    List<Restaurant> getAllForDay(@Param("day") LocalDate day);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.menus m JOIN FETCH m.dish WHERE r.id=:restaurantId AND m.menuDate=:day")
    Restaurant getByIdForDay(@Param("restaurantId") Integer restaurantId, @Param("day") LocalDate day);
}
