package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("restaurant")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer restaurantId;
    private  String name;
    private String picUrl;
    private  Float score;//评分
}
