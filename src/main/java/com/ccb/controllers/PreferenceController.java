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


    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id){
        log.info("preference detail:id={}",id);
        Preference preference=preferenceService.getByUserId(id);
        return R.success(preference);
    }
    @GetMapping("/{id}/getMenus")
    //得到个人全部菜单的id和名字
    public R getMenus(@PathVariable("id") Integer userId){
        log.info("UserId:{} 's Menu",userId);
        List<Map<Integer,String>> Menus = preferenceService.getMenus(userId);
        return R.success(Menus);
    }
    @GetMapping("/{userId}/getMenuDishes")
    //得到单个菜单的菜的id
    public R getMenuDishes(@PathVariable Integer userId,@RequestParam Integer menuId)
    {
        List<Integer> dishes=preferenceService.getMenuDishes(userId,menuId);
        return R.success(dishes);
    }
    @GetMapping("/getMenuImage/{userId}")
    public String getMenuImage(@PathVariable Integer userId,@RequestParam Integer menuId){
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
    @PostMapping("/{userId}/creatMenu")
    public R creatMenu(@PathVariable Integer userId,@RequestParam String Name){
        log.info("User:{}'s new Menu:{}",userId,Name);
        return preferenceService.creatMenu(userId,Name);
    }
    @PostMapping("/{id}/addDishToMenu")//我喜欢的菜 menuId=1  黑名单菜单 menuId=0
    public R addDishToMenu(@PathVariable Integer id,@RequestParam Integer menuId,@RequestParam Integer dishId)
    {
        log.info("User:{} add dish:{} to menu:{}",id,dishId,menuId);
        preferenceService.addToSelectMenu(id,menuId,dishId);
        return R.success();
    }

    //删除菜单
    @DeleteMapping("/{id}/deleteMenu")///deleteMenu?userId=1&menuId=2
    public R deleteMenu(@PathVariable Integer id, @RequestParam Integer menuId) {
        preferenceService.deleteMenu(id, menuId);
        return R.success();
    }
    @DeleteMapping("/{id}/deleteDishFromMenu")
    public R deleteDishFromMenu(@PathVariable Integer id,@RequestParam Integer menuId,@RequestParam Integer dishId)
    {
        preferenceService.deleteDish(id, menuId, dishId);
        return R.success();
    }

    @PutMapping("/edit")
    public R<Preference> editpreference(@RequestBody Preference preference){
        log.info("edit:preference={}",preference);

        preferenceService.updateById(preference);
        return R.success(preference);
    }
    @PutMapping("/{id}/updateMenuName")
    public R changeMenuName(@PathVariable Integer id,@RequestParam Integer menuId,@RequestParam String newMenuName){
        log.info("Change name to:{}",newMenuName);
        preferenceService.changeMenuName(id, menuId, newMenuName);
        return R.success();
    }


}
