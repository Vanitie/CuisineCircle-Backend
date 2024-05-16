package com.ccb.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeMapper {
    void addLike_comment(@Param("comment_id") Integer comment_id,@Param("user_id")Integer user_id);
    void deleteLike_comment(@Param("comment_id") Integer comment_id,@Param("user_id")Integer user_id);
    void addLike_posting(@Param("posting_id") Integer comment_id,@Param("user_id")Integer user_id);
    void deleteLike_posting(@Param("posting_id") Integer comment_id,@Param("user_id")Integer user_id);
    List<Integer> findAllPostingLike(@Param("posting_id")Integer posting_id);
    List<Integer> findAllLikeComment(@Param("comment_id")Integer comment_id);
    int getLikeCountFromPosting(@Param("posting_id")Integer posting_id);
    int getLikeCountFromComment(@Param("comment_id")Integer comment_id);
}
