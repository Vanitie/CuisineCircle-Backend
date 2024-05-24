package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.User;

import java.util.List;

public interface DishCommentService extends IService<DishComment> {
    List<User> getDishandUserInformation(long dishId, Integer userId);
}
