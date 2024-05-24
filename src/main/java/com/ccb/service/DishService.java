package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.Preference;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService  extends IService<Dish> {

    List<DishComment> getDishInformation(long dishId);

//将菜品添加到它类(待添加函数，目前未实现)

}

