package com.ccb.vo;

import com.ccb.model.pojo.Dish;
import com.ccb.model.pojo.Restaurant;
import com.ccb.model.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingVo {


    private Integer id;//主键

    private Integer userId;
    private String content;
    private String pictureUrl1;
    private String pictureUrl2;
    private String pictureUrl3;
    private String pictureUrl4;
    private String pictureUrl5;
    private String pictureUrl6;
    private String pictureUrl7;
    private String pictureUrl8;
    private String pictureUrl9;

    private Integer commentCount;
    private Integer likes;//点赞数

    private Integer preferenceId;
    private boolean isPrePreference;
    private boolean isPrePictures;
    private Restaurant restaurant;
    private Dish dish;
    private User user;


    private Timestamp createTime;
    private Timestamp updateTime;
}
