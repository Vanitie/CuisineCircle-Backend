package com.ccb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Integer id;
    private String userName;
    private String description;
    private String avatar;
}
