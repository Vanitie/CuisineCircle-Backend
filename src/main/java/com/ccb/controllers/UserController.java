package com.ccb.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccb.common.R;
import com.ccb.form.UserForm;
import com.ccb.model.pojo.User;
import com.ccb.service.ImageService;
import com.ccb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @GetMapping("/detail/{id}")
    public R<User> detail(@PathVariable("id") Integer id) {
        log.info("detail:id={}", id);
        try {
            User user = userService.getById(id);
            if (user != null) {
                return R.success(user);
            } else {
                return R.error("用户未找到");
            }
        } catch (Exception e) {
            log.error("获取用户详情时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    //展示粉丝
    @GetMapping("/{id}/fans")
    public R<List<User>> showFans(@PathVariable("id") Integer id) {
        log.info("showFans:id={}", id);
        try {
            List<Integer> fansIds = userService.getFansId(id);
            List<User> fans = userService.getUsers(fansIds);
            return R.success(fans);
        } catch (Exception e) {
            log.error("获取粉丝列表时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    //展示关注的人
    @GetMapping("/{id}/followers")
    public R<List<User>> showFollowers(@PathVariable("id") Integer id) {
        log.info("showFollowers:id={}", id);
        try {
            List<Integer> followersIds = userService.getFollowersId(id);
            List<User> followers = userService.getUsers(followersIds);
            return R.success(followers);
        } catch (Exception e) {
            log.error("获取关注列表时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    //添加关注
    @PostMapping("/{id}/follow/{followId}")
    public R<User> addFollow(@PathVariable("id") Integer id, @PathVariable("followId") Integer followId) {
        log.info("addFollow:id={}, followId={}", id, followId);
        try {
            userService.addFollow(id, followId);
            User user = userService.getByUserId(id);
            return R.success(user);
        } catch (Exception e) {
            log.error("添加关注时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    //移除关注
    @PostMapping("/{id}/unfollow/{followId}")
    public R<User> deleteFollow(@PathVariable("id") Integer id, @PathVariable("followId") Integer followId) {
        log.info("deleteFollow:id={}, followId={}", id, followId);
        try {
            userService.deleteFollow(id, followId);
            User user = userService.getByUserId(id);
            return R.success(user);
        } catch (Exception e) {
            log.error("移除关注时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    @PostMapping("/register")
    public R<User> addUser(@RequestBody UserForm userForm) {
        log.info("addUser:userForm={}", userForm);
        try {
            // 创建一个 ModelMapper 实例
            ModelMapper modelMapper = new ModelMapper();

            // 将 UserForm 对象映射到 User 对象
            User user = modelMapper.map(userForm, User.class);

            // 调用 userService 保存用户
            userService.save(user);

            return R.success(user);
        } catch (Exception e) {
            log.error("注册用户时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody UserForm userForm) {
        log.info("login:userForm={}", userForm);
        try {
            User user = userService.getUserByNameAndPassword(userForm.getUserName(), userForm.getPassword());
            if (user != null) {
                return R.success(user);
            } else {
                return R.error("用户名或密码错误");
            }
        } catch (Exception e) {
            log.error("用户登录时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    @PutMapping("/edit")
    public R<User> editUser(@RequestBody UserForm userForm) {
        log.info("editUser:userForm={}", userForm);
        try {
            Integer id = userForm.getId();
            User user = userService.getByUserId(id);

            if (user == null) {
                return R.error("用户不存在");
            }

            // 只更新非空字段
            if (userForm.getUserName() != null) {
                user.setUserName(userForm.getUserName());
            }
            if (userForm.getPassword() != null) {
                user.setPassword(userForm.getPassword());
            }
            if (userForm.getPhone() != null) {
                user.setPhone(userForm.getPhone());
            }
            if (userForm.getGender() != null) {
                user.setGender(userForm.getGender());
            }
            if (userForm.getBirthday() != null) {
                user.setBirthday(userForm.getBirthday());
            }
            if (userForm.getDeptName() != null) {
                user.setDeptName(userForm.getDeptName());
            }
            if (userForm.getSignature() != null) {
                user.setSignature(userForm.getSignature());
            }
            if (userForm.getAvatar() != null) {
                user.setAvatar(userForm.getAvatar());
            }
            if (userForm.getDescription() != null) {
                user.setDescription(userForm.getDescription());
            }

            userService.updateById(user);
            return R.success(user);
        } catch (Exception e) {
            log.error("编辑用户信息时出错: ", e);
            return R.error("服务器内部错误");
        }
    }

    @DeleteMapping("/delete")
    public R<Void> deleteUser(@RequestBody UserForm userForm) {
        log.info("deleteUser:userForm={}", userForm);
        try {
            userService.removeById(userForm.getId());
            return R.success(null);
        } catch (Exception e) {
            log.error("删除用户时出错: ", e);
            return R.error("服务器内部错误");
        }
    }
}
