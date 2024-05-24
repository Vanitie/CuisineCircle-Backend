package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.Follow;
import com.ccb.model.pojo.Posting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
    // 查询所有关注者关注的用户ID
    List<Integer> selectFollowIdsByFanId(@Param("fanId") Integer fanId);

    // 查询所有关注某用户的关注者ID
    List<Integer> selectFanIdsByFollowId(@Param("followId") Integer followId);

    //添加关注关系
    void addFollow(@Param("fanId") Integer fanId, @Param("followId") Integer followId);

    //删除关注关系
    void deleteFollow(@Param("fanId") Integer fanId, @Param("followId") Integer followId);


}
