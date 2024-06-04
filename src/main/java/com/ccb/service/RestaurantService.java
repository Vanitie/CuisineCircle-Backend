package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Restaurant;

import java.util.List;

public interface RestaurantService extends IService<Restaurant> {



    void addDish(Integer restaurantId, Integer dishId);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(Integer restaurant_id);


}
