package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.ReadHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReadHistoryMapper extends BaseMapper<ReadHistory>{
    List<ReadHistory> selectByUserId(@Param("userId") Integer userId);
}