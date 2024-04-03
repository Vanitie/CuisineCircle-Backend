package com.ccb.form;

import com.ccb.model.enums.Gender;
import com.ccb.model.pojo.Picture;
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
    private Gender gender;

    private Date birthDay;
    private String deptName;//院系
    private String signature;//个性签名
}
