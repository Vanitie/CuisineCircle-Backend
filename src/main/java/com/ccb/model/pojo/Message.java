package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@TableName("message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer id;//主键
    private Integer messageType;//消息类型，1表示点赞，2表示评论，3表示待评价
    private Integer userId;//接受消息者
    private Integer reminderId;//点赞或评论者
    private Integer dishId;//需要评价的菜品
    private Boolean read;//是否已读


    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;//消息的创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp readTime;//消息的阅读时间
}
