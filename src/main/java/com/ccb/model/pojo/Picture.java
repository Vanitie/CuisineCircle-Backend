package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture implements Serializable {
    private Integer id;
    private String picUrl;
    private Integer userId;
}