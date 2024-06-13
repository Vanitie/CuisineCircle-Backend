package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//暂弃用
@TableName("picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String picUrl;
    private Integer userId;
}
