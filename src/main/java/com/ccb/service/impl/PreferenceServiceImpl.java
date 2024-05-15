package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.PreferenceMapper;
import com.ccb.mapper.UserDishMenuMapper;
import com.ccb.mapper.UserMapper;
import com.ccb.model.pojo.Preference;
import com.ccb.model.pojo.User;
import com.ccb.model.pojo.UserDishMenu;
import com.ccb.service.PreferenceService;
import com.ccb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements PreferenceService {
    @Autowired
    PreferenceMapper preferenceMapper;
    @Autowired
    UserDishMenuMapper userDishMenuMapper;

    public void insertUserDishLike(Integer userId, Integer dishId,Integer menuId) {
        UserDishMenu userDishMenu = new UserDishMenu();
        userDishMenu.setUserId(userId);
        userDishMenu.setDishId(dishId);
        userDishMenu.setMenuId(menuId);

        userDishMenuMapper.insert(userDishMenu);
    }

    public Preference getByUserId(Integer userId)
    {
        return preferenceMapper.selectByUserId(userId);
    }

    public List<Integer> getMenuDishes(Integer userId,Integer menuId){
        return userDishMenuMapper.selectDishesByUserIdAndMenuId(userId, menuId);
    }
}
