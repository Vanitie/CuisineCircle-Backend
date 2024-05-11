package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.PictureMapper;
import com.ccb.mapper.UserMapper;
import com.ccb.model.pojo.Picture;
import com.ccb.model.pojo.User;
import com.ccb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public void saveMain(User user, List<Picture> pictures) {
        userMapper.insert(user);

        if(pictures!=null){
            for(Picture picture:pictures){
                picture.setUserId(user.getId());
                pictureMapper.insert((picture));
            }
        }
    }

    @Override
    public void removeMain(Integer id) {
        userMapper.deleteById(id);
        pictureMapper.deleteByUserId(id);
    }

    @Override
    public void updateMain(User user, List<Picture> pictures) {
        userMapper.updateById(user);

        pictureMapper.deleteByUserId(user.getId());
        if(pictures!=null){
            for(Picture picture:pictures){
                picture.setUserId(user.getId());
                pictureMapper.insert((picture));
            }
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("name", name);
        query.eq("password", password);
        return userMapper.selectOne(query);
    }

    @Override
    public User getByUserId(Integer userId){
        return getById(userId);
    }


}
