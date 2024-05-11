// PostingController.java
package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.form.UserForm;
import com.ccb.model.pojo.Posting;
import com.ccb.model.pojo.PostingComment;
import com.ccb.model.pojo.User;
import com.ccb.service.PostingService;
import com.ccb.service.PostingCommentService;
import com.ccb.service.UserService;
import com.ccb.vo.PostingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/postings")
public class PostingController {

    private final PostingService postingService;
    private final PostingCommentService postingCommentService;
    private final UserService userService;

    public PostingController(PostingService postingService, PostingCommentService postingCommentService, UserService userService) {
        this.postingService = postingService;
        this.postingCommentService = postingCommentService;
        this.userService = userService;
    }



    @PostMapping("/{id}/follow")
    public R<User> followBlogger(@PathVariable Integer id, @RequestParam("followerId") Integer followerId) {
        User user=userService.getByUserId(id);
        User follower=userService.getByUserId(followerId);
        user.setFanUserId(followerId);
        user.setFollowUserId(id);
        return R.success();

    }
    @PostMapping("/addPost")
    public void addPosting(@RequestBody Posting posting){
        postingService.addPosting(posting);
    }

    @PostMapping("/{id}/like")
    public void likePosting(@PathVariable Integer id) {
        postingService.likePosting(id);
    }

    @PostMapping("/{id}/dislike")
    public void dislikePosting(@PathVariable Integer id) {
        postingService.dislikePosting(id);
    }



    @DeleteMapping("/{id}")
    public void deletePosting(@PathVariable Integer id) {
        postingService.deletePosting(id);
    }
    @GetMapping("/{id}")
    public PostingVo getPosting(@PathVariable Integer id){
        Posting posting=postingService.getPostingById(id);
        PostingVo postingVo=new PostingVo();
        BeanUtils.copyProperties(posting,postingVo);
        return postingVo;
    }

    @GetMapping("/{postingId}/comments")
    public List<PostingComment> getCommentsForPosting(@PathVariable Integer postingId) {
        Posting posting=postingService.getPostingById(postingId);
        List<Integer>list=posting.getComments();
        List<PostingComment>result=new ArrayList<PostingComment>();
        for(Integer it:list){
            result.add(postingCommentService.getPostingCommentById(it));
        }
        return result;
    }
    @GetMapping("/{commentId}/comments")
    public List<PostingComment> getCommentsForComment(@PathVariable Integer commentId) {
        PostingComment comment=postingCommentService.getPostingCommentById(commentId);
        List<Integer>list=comment.getComments();
        List<PostingComment>result=new ArrayList<PostingComment>();
        for(Integer it:list){
            result.add(postingCommentService.getPostingCommentById(it));
        }
        return result;
    }

    @PostMapping("/comments")
    public void addCommentToPosting( @RequestBody PostingComment comment) {
        postingCommentService.addComment(comment);
    }

    @PostMapping("/{postingId}/comments/{commentId}/like")
    public void likeComment(@PathVariable Integer postingId, @PathVariable Integer commentId) {
        postingCommentService.likeComment(commentId);
    }

    @PostMapping("/{postingId}/comments/{commentId}/dislike")
    public void dislikeComment(@PathVariable Integer postingId, @PathVariable Integer commentId) {
        postingCommentService.dislikeComment(commentId);
    }

    @DeleteMapping("/{postingId}/comments/{commentId}")
    public void deleteComment(@PathVariable Integer postingId, @PathVariable Integer commentId) {
        postingCommentService.deleteComment(commentId);
    }
}
