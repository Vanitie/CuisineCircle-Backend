// PostingCommentServiceImpl.java
package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.FollowMapper;
import com.ccb.mapper.LikeMapper;
import com.ccb.mapper.PostingCommentMapper;
import com.ccb.model.pojo.PostingComment;
import com.ccb.service.PostingCommentService;
import com.ccb.vo.PostingCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingCommentServiceImpl extends ServiceImpl<PostingCommentMapper, PostingComment> implements PostingCommentService {
    @Autowired
    PostingCommentMapper postingCommentMapper;
    @Autowired
    FollowMapper followMapper;
    @Autowired
    LikeMapper likeMapper;
    @Override
    public PostingComment getPostingCommentById(Integer id) {
        return getById(id);
    }

    @Override
    public void addComment(PostingComment postingComment) {
        postingCommentMapper.insert(postingComment);

    }
    public void addFollow(Integer comment_id, Integer followId) {

    }
    public void findAllAllCommentId(Integer commentId, List<Integer> commentList){
        List<Integer> listNow= postingCommentMapper.getPostingCommentIdsByCommentId(commentId);
        if(listNow.isEmpty())return;
        else{
            commentList.addAll(listNow);
            for(Integer i:listNow){
                findAllAllCommentId(i,commentList);
            }
        }
        return;

    }





}
