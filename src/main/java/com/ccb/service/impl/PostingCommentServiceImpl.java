// PostingCommentServiceImpl.java
package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.PostingCommentMapper;
import com.ccb.model.pojo.PostingComment;
import com.ccb.service.PostingCommentService;
import com.ccb.vo.PostingCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostingCommentServiceImpl extends ServiceImpl<PostingCommentMapper, PostingComment> implements PostingCommentService {
@Autowired
PostingCommentMapper postingCommentMapper;
    @Override
    public PostingComment getPostingCommentById(Integer id) {
        return getById(id);
    }

    @Override
    public void addComment(PostingComment postingComment) {
        postingCommentMapper.insert(postingComment);

    }


    @Override
    public void likeComment(Integer commentId) {
        PostingComment comment = getById(commentId);
        Integer likes=0;
        if (comment != null) {
            likes=comment.getLikes();
            likes++;
            postingCommentMapper.updateLikes(commentId,likes);
        }

    }

    @Override
    public void dislikeComment(Integer commentId) {
        PostingComment comment = getById(commentId);
        Integer dislikes=0;
        if (comment != null) {
            dislikes=comment.getLikes();
            dislikes++;
            postingCommentMapper.updateDisLikes(commentId,dislikes);
        }
    }

    @Override
    public void deleteComment(Integer commentId) {
        postingCommentMapper.deleteById(commentId);
    }
}
