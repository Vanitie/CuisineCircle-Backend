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

import java.lang.reflect.Field;
import java.util.List;

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
    public void addUserDishLike_LikeMenu(Integer userId, Integer dishId) {
        UserDishLikes userDishLikes = new UserDishLikes();
        userDishLikes.setUserId(userId);
        userDishLikes.setDishId(dishId);

        userDishLikesMapper.insert(userDishLikes);
    }

    //再其他menu中加入菜品
    public void addUserDishLike_OtherMenu(Integer userId,long menuId,Integer dishId){
        UserDishLikes userDishLikes = new UserDishLikes();
        userDishLikes.setUserId(userId);
        userDishLikes.setDishId(dishId);
        userDishLikes.setMenuId(menuId);
        userDishLikesMapper.insert(userDishLikes);
    }

    //创建新菜单,创建编号
    public void addNewMenu(Integer userId,long menuId){
       Preference preference=preferenceMapper.selectByUserId(userId);
       List<Long> copy_menu=preference.getMenu();
       copy_menu.add(menuId);
       preference.setMenu(copy_menu);

       preferenceMapper.insert(preference);
    }

    public Preference getByUserId(Integer userId) {
        return preferenceMapper.selectByUserId(userId);
    }
    public R<Preference> sharePreference(Integer userId){
        Preference preference = preferenceMapper.selectByUserId(userId);

//        // 获取 Preference 类的所有字段
//        Field[] fields = Preference.class.getDeclaredFields();
//
//        // 遍历字段并展示属性信息
//        for (Field field : fields) {
//            try {
//                field.setAccessible(true); // 设置字段可访问
//                String fieldName = field.getName();
//                Object value = field.get(preference);
//                System.out.println(fieldName + ": " + value);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

        return R.success(preference);
    } //给到用户id，读取到用户偏好

}
