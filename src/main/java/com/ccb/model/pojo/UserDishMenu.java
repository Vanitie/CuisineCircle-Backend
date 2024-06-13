package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user_dish_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDishMenu {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;//主键
    Integer userId;
    Integer dishId;
    Integer menuId;
    String menuName;
    String menuUrl;
    boolean isCopy;
}
