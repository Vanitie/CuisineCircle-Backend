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
@RequestMapping("/restaurant")

public class RestaurantController {

    @Autowired
private RestaurantService restaurantService;
@Autowired
private DishService dishService;
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
        log.info("dish's id={}",restaurant.getRestaurantId());
        restaurantService.save(restaurant);
        return R.success();
    }

    // 在特定饭店中添加菜品
@PostMapping("/{restaurantId}/dish/add")
    public R addDishToRestaurant(@PathVariable("restaurantId") Integer restaurantId, @RequestBody Dish dish) {
        // 从数据库中获取指定ID的餐馆
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        // 检查餐馆是否存在
        if (restaurant != null) {
            // 设置菜品所属的餐馆ID
            dish.setRestaurantId(restaurant.getRestaurantId());

            // 将菜品添加到餐馆


            // 根据添加结果返回不同的响应
            if (dishService.addDish(dish)) {
                // 如果添加成功，返回成功的响应
                return R.success();
            } else {
                // 如果添加失败，返回失败的响应
                return R.error("菜品添加失败");
            }
        } else {
            // 如果餐馆不存在，返回失败的响应
            return R.error("指定的餐馆不存在");
        }
    }}
