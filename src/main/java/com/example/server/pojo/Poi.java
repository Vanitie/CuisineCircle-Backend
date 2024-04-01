package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("poi_table")//it marks using poi_table.poi_table is the name of Link of database
@Data//we use the annotation to implement the setter and getter
public class Poi {
    private String name;
    private String description;
    private Integer id;
    private Float ing;
    private Float lat;

//    public String coverURL;


}