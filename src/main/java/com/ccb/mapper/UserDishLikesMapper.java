package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.UserDishLikes;
import org.apache.ibatis.annotations.Mapper;

//关联表，管理用户喜欢的菜
@Mapper
public interface UserDishLikesMapper extends BaseMapper<UserDishLikes> {
}
