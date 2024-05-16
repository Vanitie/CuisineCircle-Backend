package com.ccb.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ccb.model.pojo.PostingComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingVo {


    private Integer id;//主键

    private Integer userId;
    private String content;
    private List<String> pictures;//存储图片URL,新定义posting_picture表存储

    private Integer likes;//点赞数
    private Integer dislikes;//点踩数
    private Long preferenceId;
    private boolean isPrePreference;
    private boolean isPrePictures;
    private List<PostingComment> comments;//评论，新建表posting_comment

    private Timestamp createTime;
    private Timestamp updateTime;
}
