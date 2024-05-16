package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishCommentMapper;
import com.ccb.mapper.DishMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.service.DishCommentService;
import com.ccb.service.DishService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class DishCommentServicelmpl extends ServiceImpl<DishCommentMapper, DishComment> implements DishCommentService {

}
