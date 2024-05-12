// PostingController.java
package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.model.pojo.Picture;
import com.ccb.model.pojo.Posting;
import com.ccb.model.pojo.PostingComment;
import com.ccb.model.pojo.User;
import com.ccb.service.PostingService;
import com.ccb.service.PostingCommentService;
import com.ccb.service.UserService;
import com.ccb.vo.PostingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
        follower.setFollowUserId(id);
        List<Picture> pictures=new ArrayList<>();
        userService.updateMain(user,pictures);
        userService.updateMain(follower,pictures);

        return R.success();

    }
    @PostMapping("/addPost")
    public R<User> addPosting(@RequestBody Posting posting){
        postingService.addPosting(posting);
        return R.success();
    }

    @PostMapping("/{id}/like")
    public R<User> likePosting(@PathVariable Integer id) {
        postingService.likePosting(id);
        return R.success();
    }

    @PostMapping("/{id}/dislike")
    public R<User>dislikePosting(@PathVariable Integer id) {
        postingService.dislikePosting(id);
        return R.success();
    }



    @DeleteMapping("/{id}")
    public R<User> deletePosting(@PathVariable Integer id) {
        postingService.deletePosting(id);
        return R.success();
    }
    @GetMapping("/{id}")
    public R<PostingVo> getPosting(@PathVariable Integer id){
        Posting posting=postingService.getPostingById(id);
        PostingVo postingVo=new PostingVo();
        BeanUtils.copyProperties(posting,postingVo);
        return R.success(postingVo);
    }

    @GetMapping("/{postingId}/comments")
    public R<List<PostingComment>> getCommentsForPosting(@PathVariable Integer postingId) {
        Posting posting=postingService.getPostingById(postingId);
        List<Integer>list=posting.getComments();
        List<PostingComment>result= new ArrayList<>();
        for(Integer it:list){
            result.add(postingCommentService.getPostingCommentById(it));
        }
        return R.success(result);
    }
    @GetMapping("/{commentId}/comments")
    public R<List<PostingComment>> getCommentsForComment(@PathVariable Integer commentId) {
        PostingComment comment=postingCommentService.getPostingCommentById(commentId);
        List<Integer>list=comment.getComments();
        List<PostingComment>result= new ArrayList<>();
        for(Integer it:list){
            result.add(postingCommentService.getPostingCommentById(it));
        }
        return R.success(result);
    }

    @PostMapping("/comments")
    public R<User> addCommentToPosting( @RequestBody PostingComment comment) {
        postingCommentService.addComment(comment);
        return R.success();
    }

    @PostMapping("/comments/{commentId}/like")
    public R<User> likeComment(@PathVariable Integer commentId) {
        postingCommentService.likeComment(commentId);
        return R.success();
    }

    @PostMapping("/comments/{commentId}/dislike")
    public R<User> dislikeComment( @PathVariable Integer commentId) {
        postingCommentService.dislikeComment(commentId);
        return R.success();
    }

    @DeleteMapping("/{postingId}/comments/{commentId}")
    public R<User> deleteComment(@PathVariable Integer postingId, @PathVariable Integer commentId) {
        postingCommentService.deleteComment(commentId);
        return R.success();
    }
}
