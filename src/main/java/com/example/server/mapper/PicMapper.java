package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Pic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PicMapper extends BaseMapper<Pic> {//this is map,extends BaseMapper
    void deleteByPicId(@Param("poiId")int poiId);
}
