package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishCommentMapper;
import com.ccb.mapper.DishMapper;
import com.ccb.mapper.UserDishAssociationMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.UserDishAssociation;
import com.ccb.model.pojo.UserDishMenu;
import com.ccb.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class DIshServiceImpl extends ServiceImpl<DishMapper,Dish>implements DishService {
    @Autowired
    private UserDishAssociationMapper userDishAssociationMapper;
    public void insertUserDishAssociation(Integer userId,Integer dishId,boolean isEat) {
        UserDishAssociation  userDishAssociation=new UserDishAssociation();
        userDishAssociation.setDishId(dishId);userDishAssociation.setUserId(userId);userDishAssociation.setEat(isEat);
        userDishAssociationMapper.insert(userDishAssociation);
    }

    //检查是否吃过，返回bool
    public boolean selectIsEat(Integer userId,Integer dishId){
        QueryWrapper<UserDishAssociation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("dish_id", dishId);

        // 使用 MyBatis-Plus 提供的 selectOne 方法查询匹配的记录
        UserDishAssociation association = userDishAssociationMapper.selectOne(queryWrapper);

        // 返回 isEat 属性，如果记录不存在返回 null
        return association != null && Boolean.TRUE.equals(association.getIsEat());
    }
}
