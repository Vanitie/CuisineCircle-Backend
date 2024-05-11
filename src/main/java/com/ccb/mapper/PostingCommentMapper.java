// PostingCommentMapper.java
package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.PostingComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostingCommentMapper extends BaseMapper<PostingComment> {
    void updateLikes(@Param("commentId") Integer commentId, @Param("likes") Integer likes);
    void updateDisLikes(@Param("commentId") Integer commentId, @Param("dislikes") Integer dislikes);

}
