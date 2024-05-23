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

public class ResturantController {
    @Autowired
private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    // 获取所有饭店列表,
    @GetMapping("/Restaurantlist")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();


        return R.success(restaurants);
    }

    // 获取特定饭店的详细信息，包括其菜品列表
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantDetails(@PathVariable("restaurantId") long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 添加新的饭店
    @PostMapping("/add")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.ok(newRestaurant);
    }

    // 在特定饭店中添加菜品
    @PostMapping("/{restaurantId}/dish/add")
    public ResponseEntity<Dish> addDishToRestaurant(@PathVariable("restaurantId") long restaurantId, @RequestBody Dish dish) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant != null) {
            dish.setRestaurant(restaurant); // 设置菜品所属的饭店
            Dish newDish = dishService.addDish(dish);
            return ResponseEntity.ok(newDish);
        } else {
            return ResponseEntity.notFound().build();
        }
}
