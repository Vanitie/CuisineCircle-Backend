package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.RestaurantMapper;
import com.ccb.model.pojo.Message;
import com.ccb.model.pojo.Restaurant;
import com.ccb.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Repository
public class RestaurantServiceImpl extends ServiceImpl<RestaurantMapper, Restaurant> implements RestaurantService{
@Autowired
private RestaurantMapper restaurantMapper;
    @Override
    public boolean save(Restaurant restaurant) {
        return super.save(restaurant);
    }


    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantMapper.selectList(null);
    }
 @Override
public Restaurant getRestaurantById(Integer id) {
    return restaurantMapper.selectById(id);
}

}

