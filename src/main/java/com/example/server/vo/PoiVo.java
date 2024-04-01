package com.example.server.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("poi_table")//it is same as class Poi
@Data
public class PoiVo {//PoiVo is used to transfer the data to the client
    private String name;
    private String description;
    private Integer id;
    private List pic;


}
