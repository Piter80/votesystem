package ru.zimin.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zimin.votesystem.repository.MenuRepository;
import ru.zimin.votesystem.repository.RestaurantRepository;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;



}
