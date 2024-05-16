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


    private Long preferenceId;
    private boolean isPrePreference;
    private boolean isPrePictures;


    @TableField(fill = FieldFill.INSERT)//记录创建时间
    private Timestamp createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

}
