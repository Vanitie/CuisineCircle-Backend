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
    @Override
    //为菜品添加tag
    public void addTagToDish(Integer dishId, Integer tagId) {
        // 查询是否已经存在 dishId 和 tagId 的组合
        QueryWrapper<DishTagAssociation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id", dishId).eq("tag_id", tagId);
        DishTagAssociation existingAssociation = dishTagAssociationMapper.selectOne(queryWrapper);

        if (existingAssociation != null) {
            // 如果存在，则更新 count 字段
            existingAssociation.setCount(existingAssociation.getCount() + 1); // 假设 count 字段需要加1
            dishTagAssociationMapper.updateById(existingAssociation);
        } else {
            // 如果不存在，则插入新的记录，初始 count 为 1
            insertDishTagAssociation(dishId, tagId, 1);
        }
    }
    @Override
    //删除菜品中的tag
    public void deleteTagFromDish(Integer dishId,Integer tagId){
        // 查询是否已经存在 dishId 和 tagId 的组合
        QueryWrapper<DishTagAssociation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id", dishId).eq("tag_id", tagId);
        DishTagAssociation existingAssociation = dishTagAssociationMapper.selectOne(queryWrapper);

        if (existingAssociation != null) {
            // 如果存在，则更新 count 字段
            if (existingAssociation.getCount() > 1) {
                existingAssociation.setCount(existingAssociation.getCount() - 1);
                dishTagAssociationMapper.updateById(existingAssociation);
            } else {
                // 如果 count 减少到 0，则删除记录
                dishTagAssociationMapper.delete(queryWrapper);
            }
        } else {
            // 如果不存在，则删除失败（这里可以抛出异常或记录日志）
            throw new IllegalArgumentException("Dish-Tag association not found.");
        }
    }
}
