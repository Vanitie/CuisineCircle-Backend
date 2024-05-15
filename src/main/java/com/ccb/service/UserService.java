package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Picture;
import com.ccb.model.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {
    void saveMain(User user, List<Picture>pictures);

    public List<Integer> getFansId(Integer id);

    public void addFollow(Integer id,Integer followId);
    public void deleteFollow(Integer id,Integer followId);
    public List<Integer> getFollowersId(Integer id);

    public List<User> getUsers(List<Integer> ids);

    void removeMain(Integer id);

    void updateMain(User user, List<Picture>pictures);
    public User getUserByNameAndPassword(String name, String password);



    public User getByUserId(Integer userId);



}
