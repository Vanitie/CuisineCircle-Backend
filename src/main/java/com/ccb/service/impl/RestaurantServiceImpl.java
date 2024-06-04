package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishRestaurantAssociationMapper;
import com.ccb.mapper.RestaurantMapper;
import com.ccb.model.pojo.DishResaurantAssociation;
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
@Autowired
DishRestaurantAssociationMapper dishRestaurantAssociationMapper;

@Override
public void addDish(Integer restaurantId, Integer dishId){
    DishResaurantAssociation dishResaurantAssociation=new DishResaurantAssociation();
    dishResaurantAssociation.setDishId(dishId);
    dishResaurantAssociation.setRestaurantId(restaurantId);
    dishRestaurantAssociationMapper.insert(dishResaurantAssociation);

}


    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantMapper.selectList(null);
    }
    @Override
    public Restaurant getRestaurantById(Integer id) {
        QueryWrapper<Restaurant> queryWrapper = new QueryWrapper<>();
        // 添加查询条件
        queryWrapper.eq("restaurant_id", id);
        return restaurantMapper.selectOne(queryWrapper);
    }

}

