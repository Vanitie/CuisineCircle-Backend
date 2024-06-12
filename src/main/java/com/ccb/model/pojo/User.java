package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.ccb.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;



@TableName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String password;
    private String phone;//电话号码
    private Boolean gender;

    private Date birthday;
    private String deptName;//院系
    private String signature;//个性签名
    private String avatar;//头像，存储图片URL
    private String description;

    private Long preferenceId;

    @TableField(fill = FieldFill.INSERT)//记录创建时间
    private Timestamp createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

}
