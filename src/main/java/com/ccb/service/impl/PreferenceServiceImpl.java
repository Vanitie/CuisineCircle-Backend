package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements PreferenceService {
    @Autowired
    PreferenceMapper preferenceMapper;
    @Autowired
    UserDishMenuMapper userDishMenuMapper;

    public void insertUserDishLike(Integer userId, Integer dishId, Integer menuId,String menuName) {
        UserDishMenu userDishMenu = new UserDishMenu();
        userDishMenu.setUserId(userId);
        userDishMenu.setDishId(dishId);
        userDishMenu.setMenuId(menuId);
        userDishMenu.setMenuName(menuName);

        userDishMenuMapper.insert(userDishMenu);
    }

    public Preference getByUserId(Integer userId) {
        return preferenceMapper.selectByUserId(userId);
    }

    //获得一个菜单里的菜
    public List<Integer> getMenuDishes(Integer userId, Integer menuId) {
        return userDishMenuMapper.selectDishesByUserIdAndMenuId(userId, menuId);
    }

    //加新菜单，菜单编号顺序从2开始递增
    public void creatMenu(Integer userId,String menuName) {
        Preference preference = preferenceMapper.selectByUserId(userId);
        Integer index = preference.getMenusIndex();
        preference.setMenusIndex(index+1);

        insertUserDishLike(userId,-1,index+1,menuName);//存储在关联表中初始菜单的dishId=-1

        List<Map<String,Integer>> copy = preference.getMenus();
        preferenceMapper.updateById(preference);
    }

    @Override
    public void shareMenu() //分享
    {

    }

    //删除菜单
    public void deleteMenu(Integer userId, Integer menuId) {
        userDishMenuMapper.deleteByUserIdAndMenuId(userId, menuId);
        Preference preference = preferenceMapper.selectByUserId(userId);
        List<Map<String,Integer>> copy = preference.getMenus();
        int index = menuId - 2;
        copy.remove(index);
        preference.setMenus(copy);
        preferenceMapper.updateById(preference);
    }
}
