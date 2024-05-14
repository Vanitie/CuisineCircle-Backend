package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.common.R;
import com.ccb.mapper.PreferenceMapper;
import com.ccb.mapper.UserDishLikesMapper;
import com.ccb.model.pojo.Preference;
import com.ccb.model.pojo.UserDishLikes;
import com.ccb.service.PreferenceBlindBoxService;
import com.ccb.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements PreferenceService {
    @Autowired
    private PreferenceMapper preferenceMapper;

    @Autowired
    private UserDishLikesMapper userDishLikesMapper;//关联表mapper

    /*
    1、在controller实现addLikeDish(): 对preference中的private List<Integer> likeDishId增添被喜欢的dishID  String LikeDishId
    2、实现sharePreference()
     */
    //添加用户喜欢的菜
    public void addUserDishLike(long userId, long dishId) {
        UserDishLikes userDishLikes = new UserDishLikes();
        userDishLikes.setUserId(userId);
        userDishLikes.setDishId(dishId);

        userDishLikesMapper.insert(userDishLikes);
    }

    //public R<Preference> sharePreference(Integer userid){} //给到用户id，读取到用户偏好

}
