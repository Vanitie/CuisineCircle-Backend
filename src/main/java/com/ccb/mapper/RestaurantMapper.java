package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.Restaurant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RestaurantMapper extends BaseMapper<Restaurant> {
    @Select("SELECT * FROM restaurant WHERE restaurant_id = #{restaurantId}")
    Restaurant selectByRestaurantId(@Param("restaurantId") Integer restaurantId);
}
