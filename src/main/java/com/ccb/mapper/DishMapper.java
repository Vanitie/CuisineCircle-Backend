package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.PostingComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    
List<Dish>getAlldishes();

    Dish getDishById(long randomId);//?

    @Select("SELECT image FROM dish WHERE id = #{dishId}")
    String selectImageById(Integer dishId);

    // 获取最大菜品ID
    @Select("SELECT MAX(id) FROM dish")
    Long getMaxDishId();

    void updatEatingInfo(Integer dishId);

    @Select("SELECT AVG(stars)FROM dish WHERE id=#{dishId}")
    Float getAverageStarsByDishId(@Param("dishId")Integer dishId);

    @Update("UPDATE dish SET stars =#{stars},eat_number=#{eatNumber} WHERE id =#{dishId}")
    void updateDishStarsWithEatNumber(@Param("dishId") Integer dishId,
                                      @Param("stars")Float stars,
                                      @Param("eatNumber")Integer eatNumber);
}
