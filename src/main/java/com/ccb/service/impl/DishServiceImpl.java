package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishCommentMapper;
import com.ccb.mapper.DishMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.User;
import com.ccb.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish>implements DishService{

    @Autowired
      DishMapper dishMapper;
    @Autowired
    DishCommentMapper dishCommentMapper;


   //显示菜品相关信息
   @Override
     public  List<DishComment>getDishInformation(long dishId){

         return dishCommentMapper.getComment(dishId);
     }
@Override
    Dish addDish(Dish dish){

}

     }
