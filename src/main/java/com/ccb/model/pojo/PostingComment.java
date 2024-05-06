package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("posting_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingComment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer postingId; // 外键，指向被评论的帖子
    private Integer parentCommentId; // 外键，指向父评论
    private String content; // 评论内容
    private Integer userId; // 外键，评论者用户ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime; // 评论创建时间
}
