package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user_dish_association")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDishAssociation {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    Integer userId;
    Integer dishId;
    boolean isEat;

    public boolean getIsEat(){return isEat;}

}
