package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@TableName("dish_restaurant_association")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishResaurantAssociation {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer restaurantId;
    private Integer dishId;
}