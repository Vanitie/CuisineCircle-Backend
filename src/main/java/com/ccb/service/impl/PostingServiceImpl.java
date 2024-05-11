// PostingServiceImpl.java
package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.PostingMapper;
import com.ccb.model.pojo.Posting;

import com.ccb.model.pojo.PostingComment;
import com.ccb.model.pojo.User;
import com.ccb.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostingServiceImpl extends ServiceImpl<PostingMapper, Posting> implements PostingService {
    @Autowired
    PostingMapper postingMapper;
    @Override
    public Posting getPostingById(Integer id) {
        return getById(id);
    }
    @Override
    public void addPosting(Posting posting){
        postingMapper.insert(posting);
    }





    @Override
    public void likePosting(Integer commentId) {
        Posting comment = getById(commentId);
        Integer likes=0;
        if (comment != null) {
            likes=comment.getLikes();
            likes++;
            postingMapper.updateLikes(commentId,likes);
        }

    }

    @Override
    public void dislikePosting(Integer commentId) {
        Posting comment = getById(commentId);
        Integer dislikes=0;
        if (comment != null) {
            dislikes=comment.getLikes();
            dislikes++;
            postingMapper.updateDisLikes(commentId,dislikes);
        }
    }



    @Override
    public void deletePosting(Integer postingId) {
        postingMapper.deleteById(postingId);
    }
}
