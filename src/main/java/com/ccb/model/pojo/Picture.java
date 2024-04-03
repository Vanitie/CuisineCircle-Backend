package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    private Integer id;
    private String picUrl;
    private Integer userId;
}
