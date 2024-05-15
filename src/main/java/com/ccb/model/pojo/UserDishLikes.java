package com.ccb.model.pojo;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_dish_likes") //关联表
public class UserDishLikes {
    @TableId
    private Long id;
    private Integer userId;
    private Long menuId;
    private Integer dishId;
}
