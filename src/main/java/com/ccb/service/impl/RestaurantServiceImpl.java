package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.RestaurantMapper;
import com.ccb.model.pojo.Restaurant;
import com.ccb.service.RestaurantService;

public class RestaurantServiceImpl extends ServiceImpl<RestaurantMapper, Restaurant> implements RestaurantService{

    @Override
    public boolean save(Restaurant restaurant) {
        return super.save(restaurant);
    }
}
