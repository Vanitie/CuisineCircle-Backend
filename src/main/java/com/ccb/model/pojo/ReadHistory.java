package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@TableName("read_history")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReadHistory implements Serializable {
    private Integer postingId;
    private Integer userId;

    @TableField(fill = FieldFill.INSERT)//记录创建时间
    private Timestamp createTime;
}
