package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.PicMapper;
import com.example.server.pojo.Pic;
import com.example.server.service.IPicService;
import org.springframework.stereotype.Service;


@Service//it is important.We use service to transfer map
public class PicServiceImpl extends ServiceImpl<PicMapper/*map we use*/, Pic> implements IPicService {
}
