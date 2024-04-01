package com.example.server.form;

import com.example.server.pojo.Pic;
import lombok.Data;

import java.util.List;
@Data
public class PoiForm {
    private String name;



    private String description;
    private Integer id;
    private Float ing;
    private Float lat;
    private List<Pic> pic;

}
