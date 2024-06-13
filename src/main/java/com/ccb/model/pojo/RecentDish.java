package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("recent_dish")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentDish implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private String userId; // 用户id
    private String dishId; // 菜品id

    @TableField(fill = FieldFill.INSERT)//记录创建时间
    private Timestamp createTime;
}
