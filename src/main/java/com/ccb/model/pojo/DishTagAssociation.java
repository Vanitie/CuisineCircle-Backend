package com.ccb.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@TableName("dish_tag_association")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishTagAssociation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;//主键
    private Integer dishId; // 菜品ID
    private Integer tagId; // 标签ID
    private Integer count; // 标签被选取的次数
}
