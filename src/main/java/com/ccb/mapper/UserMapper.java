package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUsersByIds(@Param("ids") List<Integer> ids);
    String findNameByID(@Param("id") Integer id);//这个还没有实现，不知道为什么mapper里不能添加，完全无法理解
}
