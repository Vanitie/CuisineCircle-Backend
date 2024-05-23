package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishCommentMapper;
import com.ccb.mapper.DishMapper;
import com.ccb.mapper.PostingCommentMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.PostingComment;
import com.ccb.service.DishService;
import com.ccb.service.PostingCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class DishServicelmpl extends ServiceImpl<DishMapper,Dish>implements DishService{
@Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishCommentMapper dishCommentMapper;

     @Override
     public  Dish  getdishinform(long dishId){
         Dish dish=dishMapper.getDishById(dishId);
         List<DishComment>comments =dishCommentMapper.getComment(dishId);

     }


}
