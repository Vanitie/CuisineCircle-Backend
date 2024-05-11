package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.model.pojo.Dish;
import com.ccb.service.RandomBlindBoxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/randomblindbox")
public class RandomBlindBoxController {
    @Autowired
    private RandomBlindBoxService randomBlindBoxService;

    @GetMapping("/open")
    public R<Dish> openRandomBlindBox(){
        return R.success(randomBlindBoxService.getRandomDish());
    }
}
