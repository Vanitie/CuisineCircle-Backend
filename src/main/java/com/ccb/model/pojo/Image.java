package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;
    private String type;
    private String url;


    private byte[] data;


}