package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.PostingComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    
List<Dish>getAlldishes();

    Dish getDishById(long randomId);//?

    @Select("SELECT image FROM dish WHERE id = #{dishId}")
    String selectImageById(Integer dishId);

    Long getMaxDishId();

    void updatEatingInfo(Integer dishId);
}
