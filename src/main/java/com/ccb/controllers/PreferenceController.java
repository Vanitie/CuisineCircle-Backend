package com.ccb.controllers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccb.common.R;
import com.ccb.form.UserForm;
import com.ccb.mapper.PreferenceMapper;
import com.ccb.model.pojo.Preference;
import com.ccb.model.pojo.User;
import com.ccb.service.PreferenceBlindBoxService;
import com.ccb.service.PreferenceService;
import com.ccb.service.impl.PreferenceBlindBoxServiceImpl;
import com.ccb.vo.Result;
import com.ccb.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//周子腾
@Slf4j
@RestController
@RequestMapping("/preference")
public class PreferenceController {
    @Autowired
    private PreferenceService preferenceService;

    @GetMapping("/detail")
    public R detail(@PathVariable("id") Integer id){
        log.info("preference detail:id={}",id);
        Preference preference=preferenceService.getById(id);

        return R.success(preference);
    }

    @PostMapping("/add")
    public R<Preference> add(@RequestBody Preference preference){
        log.info("dish's id={}",preference.getId());
        preferenceService.save(preference);
        return R.success();
    }

    @DeleteMapping("/delete")
    public R<Preference> delete(@PathVariable("id") Integer id){
        preferenceService.removeById(id);
        return R.success();
    }

    @PutMapping("edit")
    public R<Preference> editpreference(@RequestBody Preference preference){
        log.info("edit:preference={}",preference);

        preferenceService.updateById(preference);

        return R.success(preference);
    }

}
