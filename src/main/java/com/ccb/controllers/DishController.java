package com.ccb.controllers;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ccb.common.R;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.Preference;
import com.ccb.service.DishService;
import com.ccb.service.PreferenceBlindBoxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//赵金波
@Slf4j
@RestController
@RequestMapping("/dish")

public class DishController {
    @Autowired
    private DishService dishService;
    @GetMapping("/detail")
    public R detail(@PathVariable("id") Integer id){
        log.info("dish detail:id={}",id);
        Dish dish= dishService.getById(id);
        return R.success(dish);
    }
    @PostMapping("/add")
    public R<Dish> add(@RequestBody Dish dish){
        log.info("dish's id={}",dish.getId());
     dishService.save(dish);
        return R.success();
    }
    @DeleteMapping("/delete")
    public R<Dish> delete(@PathVariable("id") Integer id){
      dishService.removeById(id);
        return R.success();
    }

    @PutMapping("edit")
    public R<Preference> editpreference(@RequestBody Dish dish){
        log.info("edit:preference={}",dish);

        dishService.updateById(dish);

        return R.success();
    }
}
