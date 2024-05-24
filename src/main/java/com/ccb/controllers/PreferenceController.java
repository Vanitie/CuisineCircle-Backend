package com.ccb.controllers;
import com.ccb.common.R;
import com.ccb.model.pojo.Preference;
import com.ccb.service.PreferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//周子腾
@Slf4j
@RestController
@RequestMapping("/preference")
public class PreferenceController {
    @Autowired
    private PreferenceService preferenceService;


    @GetMapping("/detail:{id}")
    public R detail(@PathVariable("id") Integer id){
        log.info("preference detail:id={}",id);
        Preference preference=preferenceService.getByUserId(id);

        return R.success(preference);
    }
    @GetMapping("/getMenus:{id}")
    //得到个人全部菜单的id和名字
    public R getMenus(@PathVariable("id") Integer userId){
        log.info("UserId:{} 's Menu",userId);
        List<Map<Integer,String>> Menus = preferenceService.getMenus(userId);
        return R.success(Menus);
    }
    @GetMapping("/getMenuDishes")
    //得到单个菜单的菜的id
    public R getMenuDishes(@RequestParam Integer userId,@RequestParam Integer menuId)
    {
        List<Integer> dishes=preferenceService.getMenuDishes(userId,menuId);
        return R.success(dishes);
    }
    @GetMapping("/getMenuImage")
    public String getMenuImage(@RequestParam Integer userId,@RequestParam Integer menuId){
        log.info("Menu:{}'s Image",menuId);
        return preferenceService.getMenuUrl(userId,menuId);
    }
    @PostMapping("/add")
    public R<Preference> add(@RequestBody Preference preference){
        log.info("dish's id={}",preference.getId());
        preferenceService.save(preference);
        return R.success();
    }
    //创建菜单
    @PostMapping("/creatMenu")
    public R creatMenu(@RequestParam Integer userId,@RequestParam String Name){
        log.info("User:{}'s new Menu:{}",userId,Name);
        return preferenceService.creatMenu(userId,Name);
    }
    @PostMapping("/addDishToMenu")//我喜欢的菜 menuId=1  黑名单菜单 menuId=0
    public R addDishToMenu(@RequestParam Integer userId,@RequestParam Integer menuId,@RequestParam Integer dishId)
    {
        log.info("User:{} add dish:{} to menu:{}",userId,dishId,menuId);
        preferenceService.addToSelectMenu(userId,menuId,dishId);
        return R.success();
    }

    //删除菜单
    @DeleteMapping("/deleteMenu")///deleteMenu?userId=1&menuId=2
    public R deleteMenu(@RequestParam Integer userId, @RequestParam Integer menuId) {
        preferenceService.deleteMenu(userId, menuId);
        return R.success();
    }
    @DeleteMapping("/deleteDishFromMenu")
    public R deleteDishFromMenu(@RequestParam Integer ueserId,@RequestParam Integer menuId,@RequestParam Integer dishId)
    {
        preferenceService.deleteDish(ueserId, menuId, dishId);
        return R.success();
    }

    @PutMapping("/edit")
    public R<Preference> editpreference(@RequestBody Preference preference){
        log.info("edit:preference={}",preference);

        preferenceService.updateById(preference);
        return R.success(preference);
    }
    @PutMapping("/updateMenuName")
    public R changeMenuName(@RequestParam Integer userId,@RequestParam Integer menuId,@RequestParam String newMenuName){
        log.info("Change name to:{}",newMenuName);
        preferenceService.changeMenuName(userId, menuId, newMenuName);
        return R.success();
    }


}
