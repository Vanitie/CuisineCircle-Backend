package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@TableName("follow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer fanId;//关注者id
    private Integer followId;//被关注者id

    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;//关注时间
}
