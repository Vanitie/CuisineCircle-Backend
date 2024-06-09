package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Image;

public interface ImageService extends IService<Image> {
    public Image getImageByUrl(String url);
}
