package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Picture;
import com.ccb.model.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {
    void saveMain(User user, List<Picture>pictures);

    void removeMain(Integer id);

    void updateMain(User user, List<Picture>pictures);
    public User getUserByNameAndPassword(String name, String password);
}
