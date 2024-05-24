package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.DishTagAssociationMapper;
import com.ccb.mapper.TagMapper;
import com.ccb.model.pojo.DishTagAssociation;
import com.ccb.model.pojo.Tag;
import com.ccb.model.pojo.UserDishMenu;
import com.ccb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    // 这里可以实现一些其他的方法
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private DishTagAssociationMapper dishTagAssociationMapper;

    @Override
    public void insertDishTagAssociation(Integer dishId, Integer tagId,Integer count) {
        DishTagAssociation dishTagAssociation=new DishTagAssociation();
        dishTagAssociation.setDishId(dishId);
        dishTagAssociation.setTagId(tagId);
        dishTagAssociation.setCount(count);
        dishTagAssociationMapper.insert(dishTagAssociation);
    }
    @Override
    public void creatNewTag(String name,String description) {
        Tag tag=new Tag();
        //id的创建问题？
        tag.setName(name);tag.setDescription(description);
        tagMapper.insert(tag);
    }
    @Override
    public Tag getTag(Integer tagId){
        return tagMapper.selectById(tagId);
    }
    @Override
    public Tag getTagByName(String name)
    {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return tagMapper.selectOne(queryWrapper);
    }
    @Override
    //得到菜品的所有tag
    public List<Tag> getDishTags(Integer dishId)
    {
        // Step 1: Get all tag IDs for the given dish ID from the association table
        QueryWrapper<DishTagAssociation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id", dishId);
        List<DishTagAssociation> associations = dishTagAssociationMapper.selectList(queryWrapper);

        // Step 2: Extract tag IDs from the associations
        List<Integer> tagIds = associations.stream()
                .map(DishTagAssociation::getTagId)
                .collect(Collectors.toList());

        // Step 3: Get the tag details for the tag IDs
        if (tagIds.isEmpty()) {
            return List.of(); // Return an empty list if no tags are associated
        }
        return tagMapper.selectBatchIds(tagIds);
    }

}
