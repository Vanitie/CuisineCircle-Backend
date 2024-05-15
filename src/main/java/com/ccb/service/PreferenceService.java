package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Preference;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PreferenceService extends IService<Preference> {
    Preference getByUserId(Integer userId);
    List<Integer> getMenuDishes(Integer userId, Integer menuId);
    void insertUserDishLike(Integer userId, Integer dishId,Integer menuId);
    void deleteMenu(Integer userId,Integer menuId);
    void creatMenu(Integer userId,String MenuName);
    void shareMenu();
}
