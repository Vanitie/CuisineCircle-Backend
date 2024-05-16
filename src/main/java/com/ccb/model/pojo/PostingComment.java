package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@TableName("posting_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingComment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer postingId; // 外键，指向被评论的帖子
    private Integer parentCommentId; // 指向父评论
    private String content; // 评论内容
    private Integer userId; // 外键，评论者用户ID
    private Integer likes;
    private Integer dislikes;
    private Boolean isSubComment; // 是否为子评论
    private List<Integer>comments;

    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime; // 评论创建时间
}
