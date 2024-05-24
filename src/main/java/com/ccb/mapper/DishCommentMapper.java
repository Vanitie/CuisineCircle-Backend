package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.DishComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper

public interface DishCommentMapper extends BaseMapper<DishComment> {

@Select("SELECT*FROM dish_comments where dish_id=#{dishid}")
//SQL查询语句
    List<DishComment> getComment(long dishId);
}
