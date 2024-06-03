package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.Preference;
import com.ccb.model.pojo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService  extends IService<Dish>{
    List<Dish> getAllDishes();
boolean addDish(Dish dish);




    //List<Dish>getAllDishes();这里在实现类里没有实现暂时只能先注释掉了
}

