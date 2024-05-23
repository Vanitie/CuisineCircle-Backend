package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ccb.model.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
