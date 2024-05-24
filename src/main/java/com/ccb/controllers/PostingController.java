// PostingController.java
package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.mapper.LikeMapper;
import com.ccb.mapper.PostingCommentMapper;
import com.ccb.mapper.PostingMapper;
import com.ccb.model.pojo.Posting;
import com.ccb.model.pojo.PostingComment;
import com.ccb.model.pojo.User;
import com.ccb.service.PostingService;
import com.ccb.service.PostingCommentService;
import com.ccb.service.UserService;
import com.ccb.vo.PostingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posting")
public class PostingController {

    @Autowired
    private final PostingService postingService;
    private final PostingCommentService postingCommentService;
    private final UserService userService;
    private final LikeMapper likeMapper;
    private final PostingCommentMapper postingCommentMapper;
    private final PostingMapper postingMapper;

    public PostingController(PostingService postingService, PostingCommentService postingCommentService, UserService userService, LikeMapper likeMapper, @Qualifier("postingCommentMapper") PostingCommentMapper postingCommentMapper, @Qualifier("postingMapper") PostingMapper postingMapper) {
        this.postingService = postingService;
        this.postingCommentService = postingCommentService;
        this.userService = userService;
        this.likeMapper = likeMapper;
        this.postingCommentMapper = postingCommentMapper;
        this.postingMapper = postingMapper;
    }

    @PostMapping("postings/{fanId}/follow")
    public R<User> followBlogger(@PathVariable Integer fanId, @RequestParam("followId") Integer followId) {
        postingService.addFollow(fanId, followId);

        return R.success();

    }
    @PostMapping("/{fanId/deleteFollow}")
    public R<User> deleteBlogger(@PathVariable Integer fanId, @RequestParam("followId") Integer followId) {
        postingService.deleteFollow(fanId, followId);
        return R.success();
    }
    @PostMapping("/addPost")
    public R<User> addPosting(@RequestBody Posting posting){
        postingService.addPosting(posting);
        return R.success();
    }

    @PostMapping("/postings/{postingId}/like")
    public R<User> likePosting(@PathVariable Integer postingId, @RequestParam("userId")Integer userId) {
        likeMapper.addLike_posting(postingId,userId);
        return R.success();
    }
    @PostMapping("/postings/{postingId}/deleteLike")
    public R<User> deleteLikePosting(@PathVariable Integer postingId, @RequestParam("userId")Integer userId) {
        likeMapper.deleteLike_posting(postingId,userId);
        return R.success();
    }

    @GetMapping("/postings/{postingId}")
    public R<PostingVo> getPosting(@PathVariable Integer postingId){
        Posting posting=postingService.getPostingById(postingId);
        PostingVo postingVo=new PostingVo();
        BeanUtils.copyProperties(posting,postingVo);
        postingVo.setId(likeMapper.getLikeCountFromPosting(postingId));
        return R.success(postingVo);
    }
    @GetMapping("/postings/{postingId}/getAllPostingLiker")
    public R<List<User>> getAllPostingLiker(@PathVariable Integer postingId){
        List<Integer>newlist=likeMapper.findAllPostingLike(postingId);
        List<User>result=new ArrayList<>();
        for(Integer i:newlist){
            result.add(userService.getByUserId(i));
        }
        return R.success(result);
    }
    @GetMapping("/postings/{postingId}/getCommentByPosting")
    public R<List<PostingComment>> getCommentsByPosting(@PathVariable Integer postingId) {
        List<PostingComment>result=new ArrayList<>();
        List<Integer>nowlist=postingCommentMapper.getPostingCommentIdsByPostingId(postingId);
        for(Integer i:nowlist){
            result.add(postingCommentService.getPostingCommentById(i));
        }
        return R.success(result);
    }
    @GetMapping("/comment/{commentId}/getCommentByComment")
    public R<List<PostingComment>> getCommentsByComment(@PathVariable Integer commentId) {
        List<PostingComment>result=new ArrayList<>();
        List<Integer>nowlist=postingCommentMapper.getPostingCommentIdsByCommentId(commentId);
        for(Integer i:nowlist){
            result.add(postingCommentService.getPostingCommentById(i));
        }
        return R.success(result);
    }
    @GetMapping("/posting/{userId}/getPostingsByUserId")
    public R<List<Posting>> getPostingsByUserId(@PathVariable Integer userId) {
        List<Posting>result=new ArrayList<>();
        List<Integer>nowlist=postingMapper.getPostingByUserId(userId);
        for(Integer i:nowlist){
            result.add(postingService.getPostingById(i));
        }
        return R.success(result);
    }
    @GetMapping("/comment/{userId}/getCommentByUserId")
    public R<List<PostingComment>> getCommentByUserId(@PathVariable Integer userId){
        List<PostingComment>result=new ArrayList<>();
        List<Integer>nowlist=postingCommentMapper.getPostingCommentIdsByUserId(userId);
        for(Integer i:nowlist){
            result.add(postingCommentService.getPostingCommentById(i));
        }
        return R.success(result);
    }
    @PostMapping("/comment/{commentId}/addLike")
    public R<User> likeComment(@PathVariable Integer commentId,@RequestParam Integer userId){
        likeMapper.addLike_comment(commentId,userId);
        return R.success();
    }
    @PostMapping("/comment/{commentId}/deleteLike")
    public R<User>deleteLikeComment(@PathVariable Integer commentId,@RequestParam Integer userId){
        likeMapper.deleteLike_comment(commentId,userId);
        return R.success();
    }
    @DeleteMapping("/posting/{postingId}/deletePosting")
    public R<User>deletePosting(@PathVariable Integer postingId){
        List<Integer>first=postingCommentMapper.getPostingCommentIdsByPostingId(postingId);
        postingMapper.deleteById(postingId);
        List<Integer>allToDelete=new ArrayList<>();
        for(Integer i:first){
            postingCommentService.findAllAllCommentId(i,allToDelete);
        }
        for(Integer i:allToDelete){
            postingCommentMapper.deleteById(i);
        }
        return R.success();

    }
    @DeleteMapping("/comment/{commentId}/deleteComment")
    public R<User>deleteComment(@PathVariable Integer commentId){
        List<Integer>allToDelete=new ArrayList<>();
        postingCommentService.findAllAllCommentId(commentId,allToDelete);
        for(Integer i:allToDelete){
            postingCommentMapper.deleteById(i);
        }
        return R.success();
    }


    @PostMapping("/comments")
    public R<User> addCommentToPosting( @RequestBody PostingComment comment) {
        postingCommentService.addComment(comment);
        return R.success();
    }




}
