// PostingCommentService.java
package com.ccb.service;

import com.ccb.model.pojo.PostingComment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostingCommentService {
    PostingComment getPostingCommentById(Integer id);
    void addComment(PostingComment comment);
    void findAllAllCommentId(Integer commentId, List<Integer> commentList);


}
