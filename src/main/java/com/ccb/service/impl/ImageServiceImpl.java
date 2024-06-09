package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.ImageMapper;
import com.ccb.model.pojo.Image;
import com.ccb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
    @Autowired
    ImageMapper imageMapper;

    @Override
    public Image getImageByUrl(String url) {
        return imageMapper.selectByUrl(url);
    }
}
