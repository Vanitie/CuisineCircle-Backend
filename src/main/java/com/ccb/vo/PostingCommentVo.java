package com.ccb.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingCommentVo {


    private Integer id;
    private List<Integer> childCommentList;
    private Integer postingId; // 外键，指向被评论的帖子
    private Integer parentCommentId; // 外键，指向父评论
    private String content; // 评论内容
    private Integer userId; // 外键，评论者用户ID
    private Integer likes;
    private Integer dislikes;
    private Boolean isSubComment; // 是否为子评论
    private LocalDateTime createTime; // 评论创建时间
}
