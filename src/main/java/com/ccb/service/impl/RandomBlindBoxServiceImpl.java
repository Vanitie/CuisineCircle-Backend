package com.ccb.service.impl;

import com.ccb.mapper.DishMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.service.RandomBlindBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomBlindBoxServiceImpl implements RandomBlindBoxService {

    @Autowired // 自动注入 DishMapper 依赖
    private DishMapper dishMapper;

    // 生成随机菜品
    public Dish getRandomDish() {
        // 获取最大菜品ID
        Long maxId = dishMapper.getMaxDishId();

        // 如果数据库中没有菜品，则返回 null
        if (maxId == null || maxId == 0) {
            return null;
        }

        // 生成随机的菜品ID，范围在1到最大ID之间
        Random random = new Random();
        long randomId = random.nextInt(maxId.intValue()) + 1;

        // 根据随机ID从数据库中获取菜品对象
        return dishMapper.getDishById(randomId);
    }
}
