package com.ccb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccb.model.pojo.Preference;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface PreferenceMapper extends BaseMapper<Preference> {
    Preference selectByUserId(Integer userId);
   // Preference selectByPrice(BigDecimal price);
}
