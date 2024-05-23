package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.UserDishMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDishMenuMapper extends BaseMapper<UserDishMenu> {
    List<Integer> selectDishesByUserIdAndMenuId(@Param("userId") Integer userId, @Param("menuId") Integer menuId);
    void deleteByUserIdAndMenuId(@Param("userId") Integer userId, @Param("menuId") Integer menuId);
    String selectMenuNameByUserIdAndMenuId(@Param("userId") Integer userId, @Param("menuId") Integer menuId);
}
