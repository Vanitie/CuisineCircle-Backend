package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.FollowMapper;
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

    @Autowired
    private FollowMapper followMapper;

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
    public List<Integer> getFansId(Integer id) {
        return followMapper.selectFanIdsByFollowId(id);
    }

    @Override
    public void addFollow(Integer fanId, Integer followId) {
        followMapper.addFollow(fanId,followId);
    }

    @Override
    public void deleteFollow(Integer id, Integer followId) {
        followMapper.deleteFollow(id,followId);
    }

    @Override
    public List<Integer> getFollowersId(Integer id) {
        return followMapper.selectFollowIdsByFanId(id);
    }

    @Override
    public List<User> getUsers(List<Integer> ids) {
        return userMapper.selectUsersByIds(ids);
    }

    @Override
    public void removeMain(Integer id) {
        userMapper.deleteById(id);
        pictureMapper.deleteByUserId(id);
    }

    @Override
    public void updateMain(User user, List<Picture> pictures) {
        userMapper.updateById(user);


        if(pictures!=null){
            pictureMapper.deleteByUserId(user.getId());
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
