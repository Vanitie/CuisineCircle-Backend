package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccb.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@TableName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer phone;//电话号码
    private Gender gender;

    private Date birthDay;
    private String deptName;//院系
    private String signature;//个性签名

    private List<Posting>postings;//创作的帖子

    private String description;
}
