package com.ccb.form;

import com.ccb.model.enums.Gender;
import com.ccb.model.pojo.Picture;
import com.ccb.model.pojo.Posting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private Integer id;
    private String name;
    private String password;
    private Integer phone;//电话号码



    private String deptName;//院系
    private String signature;//个性签名
    private List<Integer> followUserId;//关注的用户
    private List<Integer> fanUserId;//粉丝

    private List<Posting>postings;//创作的帖子
   
}
