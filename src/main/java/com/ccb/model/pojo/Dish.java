package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {//便于持久化存储和传输
    private static final long serialVersionUID = 1L;

    private Long id;//主键
    private String name;//菜品名称
    private BigDecimal price;//菜品价格,使用BigDecimal避免浮点数舍入误差

    private List<String> tags;//菜品标签,在表dish_tag中
    private Float stars;//星级，0-10，每一级表示半颗星
    private String navigationUrl; // 导航地址

    private Integer restaurant_id;
    private String image;//使用string存储图片URL，节省数据库空间
    private String description;//描述信息

    /*
    private Integer spicy; // 辣度，0-10
    private Integer health; // 健康程度，0-10，清淡到油腻
    private Integer calorieIndex; // 热量指数，0-10，少-多
    private String cookingStyle;//烹饪方式
    */

    //private Long categoryId;//菜品分类id

    //private String code;//商品码

    //private Integer status;//目前是否在售
    private Integer sort;//展示顺序

    @TableField(fill = FieldFill.INSERT)//记录创建时间
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)//记录创建人
    private Long createUserId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;
}
