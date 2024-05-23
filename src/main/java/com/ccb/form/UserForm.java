package com.ccb.form;

import com.ccb.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能超过50个字符")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20个字符之间")
    private String password;

    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式不正确")
    private String phone;

    @NotNull(message = "性别不能为空")
    private Gender gender;

    @Past(message = "生日不能是未来的日期")
    private Date birthday;

    @NotBlank(message = "院系不能为空")
    private String deptName;

    @Size(max = 100, message = "个性签名长度不能超过100个字符")
    private String signature;

    private String avatar;

    private String description;

    private List<Integer> followUserId;

    private List<Integer> fanUserId;

}