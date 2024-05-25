package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user_dish_association")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDishAssociation {
    Integer id;
    Integer userId;
    Integer dishId;
    boolean isEat;
}
