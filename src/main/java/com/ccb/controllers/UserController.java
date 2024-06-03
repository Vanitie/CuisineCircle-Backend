package com.ccb.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccb.common.R;
import com.ccb.form.UserForm;
import com.ccb.model.pojo.User;
import com.ccb.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    public  R<List<User>> showFans(@PathVariable("id")Integer id){
        List<Integer> fansIds=userService.getFansId(id);
        List<User> fans=userService.getUsers(fansIds);
        return R.success(fans);
    }

    //展示关注的人
    @GetMapping("/{id}/followers")
    public  R<List<User>> showFollowers(@PathVariable("id")Integer id){
        List<Integer> followersIds=userService.getFollowersId(id);
        List<User> followers=userService.getUsers(followersIds);
        return R.success(followers);
    }

    //添加关注
    @PostMapping("/{id}/follow/{followId}")
    public R<User> addFollow(@PathVariable("id")Integer id,@PathVariable("followId")Integer followId){
        userService.addFollow(id,followId);
        User user =userService.getByUserId(id);
        return R.success(user);
    }

    //移除关注
    @PostMapping("/{id}/unfollow/{followId}")
    public R<User> deleteFollow(@PathVariable("id")Integer id,@PathVariable("followId")Integer followId){
        userService.deleteFollow(id,followId);
        User user =userService.getByUserId(id);
        return R.success(user);
    }

    @PostMapping("/register")
    public R<User> addUser(@RequestBody UserForm userForm){
        log.info("add:user:{}",userForm);
        User user=new User();
        BeanUtils.copyProperties(userForm,user);
        userService.save(user);

        return R.success(user);
    }
    @PostMapping("/login")
    public R<User>login(@RequestBody UserForm userForm){
        log.info("login:user:{}",userForm);
        User user = userService.getUserByNameAndPassword(userForm.getUserName(), userForm.getPassword());

        if (user != null) {
            return R.success(user);
        } else {
            return R.error("用户名或密码错误");
        }

    }
    @PutMapping("edit")
    public R<User> editUser(@RequestBody UserForm userForm){
        log.info("edit:user={}",userForm);
        User user=new User();
        BeanUtils.copyProperties(userForm,user);
        userService.updateById(user);

        return R.success(user);
    }

    @DeleteMapping("/delete")
    public R<Void> deleteUser(@RequestBody UserForm userForm){
        log.info("delete:id={}",userForm);
        userService.removeById(userForm.getId());
        return R.<Void>success(null);
    }

}
