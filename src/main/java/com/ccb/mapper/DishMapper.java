package com.ccb.mapper;

import com.ccb.model.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {
    @Select("SELECT MAX(id) FROM dish")
    Long getMaxDishId();

    @Select("SELECT * FROM dish WHERE id = #{id}")
    Dish getDishById(@Param("id") long id);
}
