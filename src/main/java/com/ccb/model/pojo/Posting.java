package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@TableName("posting")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posting implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;//主键

    private Integer userId;
    private String content;
    private List<String> pictures;//存储图片URL,新定义posting_picture表存储

    private Integer likes;//点赞数
    private Integer dislikes;//点踩数

    private List<PostingComment> comments;//评论，新建表posting_comment

    @TableField(fill = FieldFill.INSERT)//记录创建时间
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
