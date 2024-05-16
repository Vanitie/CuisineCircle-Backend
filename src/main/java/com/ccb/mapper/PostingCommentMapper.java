// PostingCommentMapper.java
package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.PostingComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostingCommentMapper extends BaseMapper<PostingComment> {
    List<Integer>getPostingCommentIdsByPostingId(@Param("postingId") Integer postingId);
    List<Integer>getPostingCommentIdsByUserId(@Param("userId") Integer userId);
    List<Integer>getPostingCommentIdsByCommentId(@Param("commentId")Integer commentId);


}
