package com.ccb.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccb.form.UserForm;
import com.ccb.model.pojo.User;
import com.ccb.service.PictureService;
import com.ccb.service.UserService;
import com.ccb.vo.Result;
import com.ccb.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//这个controller是没用的
@Slf4j
@RestController
@RequestMapping("/ccb")
public class CCBController {

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

    @GetMapping("/list")//list?pageNum=1&pageSize=30
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "100") int pageSize){
        log.info("list:pageNum={},pageSize={}",pageNum,pageSize);
        Page<User>page=new Page<User>(pageNum,pageSize);
        IPage<User>pageResult=userService.page(page);
        List<User>users=pageResult.getRecords();

        List userVos=new ArrayList<>();
        for(User user:users){
            UserVo userVo=new UserVo();
            QueryWrapper query=new QueryWrapper<>();
            query.eq("user_id",user.getId());
            List pictures = pictureService.list(query);
            BeanUtils.copyProperties(user,userVo);
            userVo.setPictures(pictures);
            userVos.add(userVo);
        }
        pageResult.setRecords(userVos);
        return Result.success(pageResult);
    }
    @GetMapping("/detail/{id}")//detail/123
    public Result detail(@PathVariable("id") Integer id){
        log.info("detail:id={}",id);
        User user=userService.getById(id);
        UserVo userVo=new UserVo();
        QueryWrapper query=new QueryWrapper<>();
        query.eq("user_id",user.getId());
        List pictures = pictureService.list(query);
        BeanUtils.copyProperties(user,userVo);
        userVo.setPictures(pictures);
        return Result.success(userVo);
    }
    @PutMapping("/edit/{id}")
    public Result edit(@RequestBody UserForm userForm,@PathVariable("id") int id){
        log.info("edit:user={}",userForm);
        User user=new User();
        BeanUtils.copyProperties(userForm,user);
        user.setId(id);
        //userService.updateMain(user,userForm.getPictures());
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(userForm,userVo);
        return Result.success(userVo);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") int id){
        log.info("delete:id={}",id);
        userService.removeMain(id);
        return Result.success();
    }
}
