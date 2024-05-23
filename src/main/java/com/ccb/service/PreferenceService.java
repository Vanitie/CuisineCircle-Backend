package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.common.R;
import com.ccb.model.pojo.Preference;

import java.util.List;
import java.util.Map;


public interface PreferenceService extends IService<Preference> {
    Preference getByUserId(Integer userId);
    List<Integer> getMenuDishes(Integer userId, Integer menuId);
    void insertUserDishLike(Integer userId, Integer dishId,Integer menuId,String menuName);
    void deleteMenu(Integer userId,Integer menuId);
    R<Preference> creatMenu(Integer userId, String menuName);
    void shareMenu();

    List<Map<Integer,String>> getMenu(Integer userId);
    void addToDisLkeMenu(Integer userId,Integer dishId);
    void addToLkeMenu(Integer userId,Integer dishId);

    String getMenuUrl(Integer menuId);
}
