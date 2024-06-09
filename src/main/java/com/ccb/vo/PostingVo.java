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
    private String pictureUrl1;
    private String pictureUrl2;
    private String pictureUrl3;
    private String pictureUrl4;
    private String pictureUrl5;
    private String pictureUrl6;
    private String pictureUrl7;
    private String pictureUrl8;
    private String pictureUrl9;


    private Integer likes;//点赞数

    private Long preferenceId;
    private boolean isPrePreference;
    private boolean isPrePictures;


    private Timestamp createTime;
    private Timestamp updateTime;
}
