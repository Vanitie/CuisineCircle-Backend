package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private Integer id;

    private String name;
    private String type;



    private byte[] data;


}