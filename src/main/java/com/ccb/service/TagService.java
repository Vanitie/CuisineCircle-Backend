package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    // 这里可以定义一些其他的方法
    void insertDishTagAssociation(Integer dishId, Integer tagId,Integer count);
    void creatNewTag(String name,String description);
    Tag getTag(Integer tagId);
    Tag getTagByName(String name);
    List<Tag> getDishTags(Integer dishId);
}