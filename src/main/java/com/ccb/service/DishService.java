package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.Preference;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService  extends IService<Dish> {

    List<DishComment> getDishInformation(long dishId);


}

