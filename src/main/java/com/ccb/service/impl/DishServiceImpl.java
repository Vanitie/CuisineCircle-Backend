package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishMapper;
import com.ccb.mapper.TagMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class DIshServiceImpl extends ServiceImpl<DishMapper,Dish>implements DishService {
    @Autowired
    TagMapper tagMapper;
@Autowired
DishMapper dishMapper;
    @Override
    public List<Dish>getAllDishes(){
       return dishMapper.selectList(null);

    }


    public Dish getDishInformation(Integer dishId){
        return dishMapper.selectById(dishId) ;
    }

    public void getEatnumber(Integer dishId,boolean isEat){
        if(isEat){
            Dish dish=new Dish();
            dish.setEatNumber(dish.getEatNumber()+1);
            dishMapper.updateById(dish);
    }
}}
