// PostingController.java
package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.mapper.*;
import com.ccb.model.pojo.*;
import com.ccb.service.MessageService;
import com.ccb.service.PostingService;
import com.ccb.service.PostingCommentService;
import com.ccb.service.UserService;
import com.ccb.vo.PostingCommentVo;
import com.ccb.vo.PostingVo;
import com.ccb.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class PostingController {

    @Autowired
    private PostingService postingService;
    @Autowired
    private PostingCommentService postingCommentService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private PostingCommentMapper postingCommentMapper;
    @Autowired
    private PostingMapper postingMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReadHistoryMapper readHistoryMapper;


    @PostMapping("/posting/{fanId}/follow")
    public R<User> followBlogger(@PathVariable Integer fanId, @RequestParam("followId") Integer followId) {
        postingService.addFollow(fanId, followId);//fanId是粉丝，followId是被关注着
        Message message = new Message();
        message.setMessageType(3);
        message.setUserId(followId);
        message.setReminderId(fanId);
        message.setName(userMapper.findNameByID(fanId));
        message.setOutline("关注了你");
        messageService.createMessage(message);
        return R.success();

    }
    @PostMapping("/posting/{fanId}/deleteFollow")
    public R<User> deleteBlogger(@PathVariable Integer fanId, @RequestParam("followId") Integer followId) {
        postingService.deleteFollow(fanId, followId);
        return R.success();
    }
    @PostMapping("/posting/addPost")
    public R<User> addPosting(@RequestBody Posting posting){
        postingService.addPosting(posting);
        ReadHistory readHistory = new ReadHistory();
        readHistory.setPostingId(posting.getId());
        readHistory.setUserId(posting.getUserId());
        readHistoryMapper.insert(readHistory);


        return R.success();
    }
    @GetMapping("/posting/history")
    public R<List<ReadHistory>> getPostingHistory(@RequestParam("userId") Integer userId){
        List<ReadHistory>result=readHistoryMapper.selectByUserId(userId);

        return R.success(result);
    }

    @PostMapping("/posting/{postingId}/like")
    public R<User> likePosting(@PathVariable Integer postingId, @RequestParam("userId")Integer userId) {
        likeMapper.addLike_posting(postingId,userId);
        Integer postingSender=postingService.getPostingSenderByPostingId(postingId);
        Message message = new Message();
        message.setMessageType(1);
        message.setUserId(postingSender);
        message.setName(userMapper.findNameByID(userId));
        message.setOutline("点赞了你的帖子");
        message.setReminderId(userId);
        messageService.createMessage(message);
        return R.success();

    }
    @PostMapping("/posting/{postingId}/deleteLike")
    public R<User> deleteLikePosting(@PathVariable Integer postingId, @RequestParam("userId")Integer userId) {
        likeMapper.deleteLike_posting(postingId,userId);
        return R.success();
    }

    @GetMapping("/posting/{postingId}")
    public R<PostingVo> getPosting(@PathVariable Integer postingId){
        Posting posting=postingService.getPostingById(postingId);
        PostingVo postingVo=new PostingVo();
        BeanUtils.copyProperties(posting,postingVo);
        postingVo.setLikes(likeMapper.getLikeCountFromPosting(postingId));
        return R.success(postingVo);
    }
    @GetMapping("/posting/{postingId}/getAllPostingLiker")
    public R<List<User>> getAllPostingLiker(@PathVariable Integer postingId){
        List<Integer>newlist=likeMapper.findAllPostingLike(postingId);
        List<User>result=new ArrayList<>();
        for(Integer i:newlist){
            result.add(userService.getByUserId(i));
        }
        return R.success(result);
    }
    @GetMapping("/comment/{commentId}/getAllCommentLiker")
    public R<List<User>> getAllCommentLiker(@PathVariable Integer commentId){
        List<Integer>newlist=likeMapper.findAllLikeComment(commentId);
        List<User>result=new ArrayList<>();
        for(Integer i:newlist){
            result.add(userService.getByUserId(i));
        }
        return R.success(result);
    }
    @GetMapping("/posting/{postingId}/getCommentByPosting")
    public R<List<PostingCommentVo>> getCommentsByPosting(@PathVariable Integer postingId) {
        List<PostingCommentVo>result=new ArrayList<>();
        List<Integer>nowlist=postingCommentMapper.getPostingCommentIdsByPostingId(postingId);
        for(Integer i:nowlist){
            PostingComment postingComment=postingCommentService.getPostingCommentById(i);
            postingComment.setLikes(likeMapper.getLikeCountFromPosting(postingComment.getId()));
            PostingCommentVo postingCommentVo=new PostingCommentVo();
            postingCommentVo.setPostingComment(postingComment);
            Integer userId=postingComment.getUserId();
            UserVo userVo=new UserVo();
            User user=userService.getByUserId(userId);
            BeanUtils.copyProperties(user,userVo);
            List<Integer>newlist=likeMapper.findAllLikeComment(i);
            postingCommentVo.setLikeUser(newlist);
            postingCommentVo.setUserVo(userVo);
            result.add(postingCommentVo);
        }
        return R.success(result);
    }
    @GetMapping("/comment/{commentId}/getCommentByComment")
    public R<List<PostingComment>> getCommentsByComment(@PathVariable Integer commentId) {
        List<PostingComment>result=new ArrayList<>();
        List<Integer>nowlist=postingCommentMapper.getPostingCommentIdsByCommentId(commentId);
        for(Integer i:nowlist){
            PostingComment postingComment=postingCommentService.getPostingCommentById(i);
            postingComment.setLikes(likeMapper.getLikeCountFromPosting(postingComment.getId()));
            result.add(postingComment);
        }
        return R.success(result);
    }
    @GetMapping("/posting/{userId}/getPostingsByUserId")
    public R<List<PostingVo>> getPostingsByUserId(@PathVariable Integer userId) {
        List<PostingVo>result=new ArrayList<>();
        List<Integer>nowlist=postingMapper.getPostingByUserId(userId);
        for(Integer i:nowlist){
            Posting posting=postingService.getPostingById(i);
            PostingVo postingVo=new PostingVo();
            BeanUtils.copyProperties(posting,postingVo);
            postingVo.setLikes(likeMapper.getLikeCountFromPosting(posting.getId()));
            result.add(postingVo);
        }
        return R.success(result);
    }
    @GetMapping("/comment/{userId}/getCommentByUserId")
    public R<List<PostingComment>> getCommentByUserId(@PathVariable Integer userId){
        List<PostingComment>result=new ArrayList<>();
        List<Integer>nowlist=postingCommentMapper.getPostingCommentIdsByUserId(userId);
        for(Integer i:nowlist){
            PostingComment postingComment=postingCommentService.getPostingCommentById(i);
            postingComment.setLikes(likeMapper.getLikeCountFromPosting(postingComment.getId()));
            result.add(postingComment);
        }
        return R.success(result);
    }
    @PostMapping("/comment/{commentId}/addLike")
    public R<User> likeComment(@PathVariable Integer commentId,@RequestParam Integer userId){
        likeMapper.addLike_comment(commentId,userId);
        Integer commentSender =postingCommentService.getCommentSenderByCommentId(commentId);
        Message message = new Message();
        message.setMessageType(1);
        message.setUserId(commentSender);
        message.setReminderId(userId);
        message.setName(userMapper.findNameByID(userId));
        message.setOutline("点赞了你的评论");
        messageService.createMessage(message);
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
    @GetMapping("/posting/getSeveral")
    public R<List<PostingVo>> getSeveralPosting(@RequestParam Integer nowCount){
        List<PostingVo>result=new ArrayList<>();
        Integer maxId=postingService.getMaxPostingId();

        int count=nowCount*10;
        int i=maxId;
        while(count>0){
            if(i<=0)break;
            PostingVo postingVo=new PostingVo();
            Posting posting=postingService.getPostingById(i);
            BeanUtils.copyProperties(posting,postingVo);
            postingVo.setLikes(likeMapper.getLikeCountFromPosting(posting.getId()));
            result.add(postingVo);
            i--;
            count--;
        }

        return R.success(result);
    }
    @PostMapping("/posting")
    public R<User> addCommentToPosting( @RequestBody PostingComment comment) {
        postingCommentService.addComment(comment);
        Message message = new Message();
        message.setMessageType(2);
        message.setUserId(postingService.getPostingSenderByPostingId(comment.getPostingId()));
        message.setReminderId(comment.getUserId());
        message.setOutline("回复了你的评论"+comment.getContent());
        message.setName(userMapper.findNameByID(comment.getUserId()));
        messageService.createMessage(message);
        return R.success();
    }
    @PostMapping("/comment")
    public R<User> addCommentToPostingComment( @RequestBody PostingComment comment) {
        postingCommentService.addComment(comment);
        Message message = new Message();
        message.setMessageType(2);
        message.setUserId(postingCommentService.getCommentSenderByCommentId(comment.getCommentId()));
        message.setReminderId(comment.getUserId());
        message.setName(userMapper.findNameByID(comment.getUserId()));
        message.setOutline("回复了你的评论"+comment.getContent());
        messageService.createMessage(message);
        return R.success();

    }



}
