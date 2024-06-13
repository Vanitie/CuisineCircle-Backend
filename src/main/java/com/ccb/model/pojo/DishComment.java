package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("dish_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishComment implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer DishId; // 外键，指向被评论的菜品
    private Integer parentCommentId; // 外键，指向父评论
    private String content; // 评论内容
    private Integer userId; // 外键，评论者用户ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime; // 评论创建时间
}
