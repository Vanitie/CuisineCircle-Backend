package com.ccb.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable {
    private Integer restaurant_id;
    private  String name;
    private String picUrl;
    private  Float score;//评分


}
