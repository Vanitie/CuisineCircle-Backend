package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishCommentMapper;
import com.ccb.model.pojo.DishComment;
import com.ccb.model.pojo.User;
import com.ccb.service.DishCommentService;
import com.ccb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class DishCommentServiceImpl extends ServiceImpl<DishCommentMapper, DishComment> implements DishCommentService {
    @Autowired
    DishCommentMapper dishCommentMapper;
    @Autowired
    UserService userService;
    @Override
    public List<User> getDishandUserInformation(long dishId, Integer userId) {
        // 获取菜品的评价数据

        List<DishComment> dishComments = dishCommentMapper.getComment(dishId);

        // 根据用户ID筛选评价数据
        List<User> users = new ArrayList<>();
        for (DishComment comment : dishComments) {
            if (userService.getByUserId(userId).equals(userId)  ) {
                // 根据用户ID获取用户信息，并将其添加到列表中
                User user =  userService.getByUserId( userId);
                users.add(user);
            }
        }

        return users;
    }


}

