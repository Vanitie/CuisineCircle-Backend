package com.ccb.service.impl;

import com.ccb.mapper.PostingCommentMapper;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// PostingCommentMapperImpl.java
@Repository
@Data
public abstract class PostingCommentMapperImpl implements PostingCommentMapper {

    private final SqlSession sqlSession;

    @Autowired
    public PostingCommentMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void updateLikes(Integer commentId, Integer likes) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("commentId", commentId);
        paramMap.put("likes", likes);
        sqlSession.update("com.example.mapper.PostingCommentMapper.updateLikes", paramMap);
    }
    @Override
    public void updateDisLikes(Integer commentId, Integer dislikes) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("commentId", commentId);
        paramMap.put("likes", dislikes);
        sqlSession.update("com.example.mapper.PostingCommentMapper.updateDisLikes", paramMap);
    }
}
