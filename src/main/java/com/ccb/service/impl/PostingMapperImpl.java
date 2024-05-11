package com.ccb.service.impl;

import com.ccb.mapper.PostingMapper;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
@Data
public abstract class PostingMapperImpl implements PostingMapper {
    private final SqlSession sqlSession;

    @Autowired
    public PostingMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void updateLikes(Integer commentId, Integer likes) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("commentId", commentId);
        paramMap.put("likes", likes);
        sqlSession.update("com.example.mapper.PostingMapper.updateLikes", paramMap);
    }
    @Override
    public void updateDisLikes(Integer commentId, Integer dislikes) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("commentId", commentId);
        paramMap.put("likes", dislikes);
        sqlSession.update("com.example.mapper.PostingMapper.updateDisLikes", paramMap);
    }
}
