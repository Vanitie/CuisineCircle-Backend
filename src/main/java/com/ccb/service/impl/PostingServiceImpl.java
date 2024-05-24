// PostingServiceImpl.java
package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.FollowMapper;
import com.ccb.mapper.LikeMapper;
import com.ccb.mapper.PostingMapper;
import com.ccb.model.pojo.Posting;

import com.ccb.model.pojo.PostingComment;
import com.ccb.model.pojo.User;
import com.ccb.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class PostingServiceImpl extends ServiceImpl<PostingMapper, Posting> implements PostingService {
    @Autowired
    PostingMapper postingMapper;
    @Autowired
    FollowMapper followMapper;
    @Autowired
    LikeMapper likeMapper;
    @Override
    public Posting getPostingById(Integer id) {
        return getById(id);
    }
    @Override
    public void addPosting(Posting posting){
        postingMapper.insert(posting);
    }

    public void addFollow(Integer fanId, Integer followerId){
        followMapper.addFollow(fanId, followerId);
    }
    public void deleteFollow(Integer postingId, Integer followerId){
        followMapper.deleteFollow(postingId, followerId);
    }
    public Integer getPostingSenderByPostingId(Integer postingId){
        return postingMapper.getUserIdByPostingId(postingId);
    }













}
