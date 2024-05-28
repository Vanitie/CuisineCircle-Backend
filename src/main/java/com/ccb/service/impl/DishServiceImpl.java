package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishMapper;
import com.ccb.mapper.TagMapper;
import com.ccb.mapper.UserDishAssociationMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.UserDishAssociation;
import com.ccb.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish>implements DishService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private UserDishAssociationMapper userDishAssociationMapper;

    @Override
    public List<Dish> getAllDishes() {
        return dishMapper.selectList(null);
    }

    public void insertUserDishAssociation(Integer userId, Integer dishId, boolean isEat) {
        UserDishAssociation userDishAssociation = new UserDishAssociation();
        userDishAssociation.setDishId(dishId);
        userDishAssociation.setUserId(userId);
        userDishAssociation.setEat(isEat);
        userDishAssociationMapper.insert(userDishAssociation);
    }

    //检查是否吃过，返回bool
    public boolean selectIsEat(Integer userId, Integer dishId) {
        QueryWrapper<UserDishAssociation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("dish_id", dishId);


        // 使用 MyBatis-Plus 提供的 selectOne 方法查询匹配的记录
        UserDishAssociation association = userDishAssociationMapper.selectOne(queryWrapper);

        // 返回 isEat 属性，如果记录不存在返回 null
        return association != null && Boolean.TRUE.equals(association.getIsEat());
    }


    public Dish getDishInformation(Integer dishId) {
        return dishMapper.selectById(dishId);
    }

    public void getEatnumber(Integer dishId, boolean isEat) {
        if (isEat) {
            Dish dish = new Dish();
            dish.setEatNumber(dish.getEatNumber() + 1);
            dishMapper.updateById(dish);
        }
    }
}
