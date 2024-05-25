package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.Restaurant;
import com.ccb.service.DishService;
import com.ccb.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ResturantController")

public class RestaurantController {

    @Autowired
private RestaurantService restaurantService;

//    @Autowired
//    private DishService;

    // 获取所有饭店列表,
    @GetMapping("/Restaurantlist")
    public R<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();


        return R.success(restaurants);
    }

    // 获取特定饭店的详细信息，包括其菜品列表
@GetMapping("{restaurantId}")
    public R<Restaurant> getRestaurantDetails(@PathVariable("restaurantId")Integer restaurant_id) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurant_id);
        if (restaurant != null) {
            return R.success(restaurant) ;
        } else {
            return R.error("not found");
        }
    }



    // 添加新的饭店
    @PostMapping("/add")
    public R<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        log.info("dish's id={}",restaurant.getRestaurant_id());
        restaurantService.save(restaurant);
        return R.success();
    }

    // 在特定饭店中添加菜品
    @PostMapping("/{restaurantId}/dish/add")
    public R<Dish> addDishToRestaurant(@PathVariable("restaurantId") Integer restaurantId, @RequestBody Dish dish) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant != null) {
            Integer restaurant_id = restaurant.getRestaurant_id();
            dish.setRestaurant_id(restaurant_id); // 设置菜品所属的饭店

            return R.success();//待修改
        } else {
            return R.success();//待修改
        }
}}