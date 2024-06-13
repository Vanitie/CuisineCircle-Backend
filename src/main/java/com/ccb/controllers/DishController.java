package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.Preference;
import com.ccb.model.pojo.Tag;
import com.ccb.service.DishService;
import com.ccb.service.PostingService;
import com.ccb.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//赵金波
@Slf4j
@RestController
@RequestMapping("/dish")

public class DishController {

    @Autowired
    TagService tagService;
    @Autowired
     DishService dishService;
    @Autowired
    PostingService postingService;


    @GetMapping("/detail/{id}")
    public R<Dish>detail(@PathVariable("id") Integer id){
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
        log.info("edit:dish={}",dish);

        dishService.updateById(dish);

        return R.success();
    }

    //查询菜品的Tag信息
    @GetMapping("/{id}/tag")
    public List<String> returnTags(@PathVariable("id") Integer dishId)
    {
        List<Tag> tags=tagService.getDishTags(dishId);
        return tags.stream()
                .map(Tag::getName)  // 获取每个 Tag 对象的 name 属性
                .collect(Collectors.toList());  // 收集到 List 中
    }

    public void deleteTags(Integer tagId, Integer dishId
    ){

        tagService.deleteTagFromDish(dishId,tagId);

    }
    @GetMapping("/{id}/stars")
      public    Float getDishStars(@PathVariable("id") Integer dishId){
        log.info("dish");
        return dishService.getStarById(dishId);
    }

}

