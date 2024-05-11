// PostingCommentService.java
package com.ccb.service;

import com.ccb.model.pojo.PostingComment;
import org.springframework.stereotype.Service;

@Service
public interface PostingCommentService {
    PostingComment getPostingCommentById(Integer id);
    void addComment(PostingComment comment);

    void likeComment(Integer commentId);
    void dislikeComment(Integer commentId);
    void deleteComment(Integer commentId);
}
