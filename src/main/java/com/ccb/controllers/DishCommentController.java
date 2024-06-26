package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.DishComment;
import com.ccb.service.DishCommentService;
import com.ccb.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/dishcomment")

public class DishCommentController {
    @Autowired
    private DishCommentService dishCommentService;

    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id){
        log.info("dishcomment detail:id={}",id);
        DishComment dishComment= dishCommentService.getById(id);
        return R.success(dishComment);
    }
    @PostMapping("/add")
    public R<DishComment> add(@RequestBody DishComment dishComment){
        log.info("dish's id={}",dishComment.getId());
        dishCommentService.save(dishComment);
        return R.success(dishComment);
    }
    @DeleteMapping("/delete")
    public R<Dish> delete(@PathVariable("id") Integer id){
        dishCommentService.removeById(id);
        return R.success();
    }

    @PutMapping("edit")
    public R<DishComment> editpreference(@RequestBody DishComment dishComment){
        log.info("edit:dishComment={}",dishComment);

        dishCommentService.updateById(dishComment);

        return R.success(dishComment);
    }


}
