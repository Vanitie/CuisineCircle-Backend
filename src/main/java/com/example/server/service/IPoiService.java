package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Pic;
import com.example.server.pojo.Poi;

import java.util.List;

public interface IPoiService extends IService<Poi> {
    void saveMain(Poi poi, List<Pic>pic);
    void deleteMain(Integer id);
    void updateMain(Poi poi, List<Pic>pics);
}
