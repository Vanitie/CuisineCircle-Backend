package com.ccb.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccb.common.R;
import com.ccb.form.UserForm;
import com.ccb.mapper.UserMapper;
import com.ccb.model.pojo.User;
import com.ccb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/detail/{id}")
    public R<User> detail(@PathVariable("id")Integer id){
        log.info("detail:id={}",id);
        User user=userService.getById(id);
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
        User user = userService.getUserByNameAndPassword(userForm.getName(), userForm.getPassword());

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
