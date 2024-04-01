package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Poi;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PoiMapper extends BaseMapper<Poi> {//this is map,extends BaseMapper
}
