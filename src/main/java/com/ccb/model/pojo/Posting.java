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

@TableName("posting")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posting implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;//主键

    private Integer userId;
    private String content;



    private Integer preferenceId;
    private boolean isPrePreference;
    private boolean isPrePictures;
    private String pictureUrl1;
    private String pictureUrl2;
    private String pictureUrl3;
    private String pictureUrl4;
    private String pictureUrl5;
    private String pictureUrl6;
    private String pictureUrl7;
    private String pictureUrl8;
    private String pictureUrl9;


    @TableField(fill = FieldFill.INSERT)//记录创建时间
    private Timestamp createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

}
