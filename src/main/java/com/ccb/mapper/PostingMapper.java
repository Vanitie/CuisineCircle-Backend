// PostingMapper.java
package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.Posting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostingMapper extends BaseMapper<Posting> {
    void updateLikes(@Param("commentId") Integer commentId, @Param("likes") Integer likes);
    void updateDisLikes(@Param("commentId") Integer commentId, @Param("dislikes") Integer dislikes);
}
