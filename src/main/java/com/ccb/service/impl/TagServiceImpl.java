package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.TagMapper;
import com.ccb.model.pojo.Tag;
import com.ccb.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    // 这里可以实现一些其他的方法
}
