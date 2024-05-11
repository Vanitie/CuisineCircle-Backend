package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@TableName("preference")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preference implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private List<Integer> likeDishId;//之前选过的菜，并且点了喜欢

    private BigDecimal price;//平均价格
    private Integer spicy;//辣度，0-10
    private Integer sweet;//甜度，0-10
    private Integer sour;//酸度，0-10
    private Integer health;//健康程度，0-10，清淡到油腻
    private Integer calorieIndex;//热量多少，0-10，少-多
    private Map<String, Integer> weightedCuisinePreferences; // 加权菜系表
    private Map<String, Integer> cookingStylePreferences; // 烹饪风格偏好
    private Map<String, Integer> restaurantPreferences; // 餐厅偏好

}
