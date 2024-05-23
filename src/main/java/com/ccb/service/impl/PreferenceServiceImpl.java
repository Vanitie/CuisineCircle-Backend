package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.common.R;
import com.ccb.mapper.DishMapper;
import com.ccb.mapper.PreferenceMapper;
import com.ccb.mapper.UserDishMenuMapper;
import com.ccb.model.pojo.Preference;
import com.ccb.model.pojo.UserDishMenu;
import com.ccb.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements PreferenceService {
    @Autowired
    PreferenceMapper preferenceMapper;
    @Autowired
    UserDishMenuMapper userDishMenuMapper;
    @Autowired
    DishMapper dishMapper;

    @Override
    public void changeMenuName(Integer userId, Integer menuId,String menuName){
        userDishMenuMapper.updateMenuNameByUserIdAndMenuId(userId,menuId,menuName);
    }
    public static final Integer MAX_MENUS = 10000;
    public void insertUserDishLike(Integer userId, Integer dishId, Integer menuId,String menuName) {
        UserDishMenu userDishMenu = new UserDishMenu();
        userDishMenu.setUserId(userId);
        userDishMenu.setDishId(dishId);
        userDishMenu.setMenuId(menuId);
        userDishMenu.setMenuName(menuName);
        String menuUrl=dishMapper.getDishById(dishId).getImage();
        userDishMenu.setMenuUrl(menuUrl);
        userDishMenuMapper.insert(userDishMenu);
    }
    public Preference getByUserId(Integer userId) {
        return preferenceMapper.selectByUserId(userId);
    }
    //获得一个菜单里的菜
    public List<Integer> getMenuDishes(Integer userId, Integer menuId) {
        return userDishMenuMapper.selectDishesByUserIdAndMenuId(userId, menuId);
    }

    //加新菜单，菜单编号顺序从2开始递增，但并无实际意义（删除可以删除其中任意一个）上限不超过10000(MAX_MENUS)
    public R<Preference> creatMenu(Integer userId, String menuName) {
        Preference preference = preferenceMapper.selectByUserId(userId);
        Integer index = preference.getMenusIndex();

        //限制菜单数量
        if(index>MAX_MENUS)
        {
            List<Map<Integer, String>> menuList= getMenus(userId);
            Set<Integer> usedMenuIds = new HashSet<>();
            for (Map<Integer, String> menuMap : menuList) {
                usedMenuIds.add(menuMap.keySet().iterator().next());
            }
            index = findUnusedIndex(usedMenuIds);
        }
        if(index==-1)
            return R.error("创建菜单失败-菜单上限");
        preference.setMenusIndex(index+1);
        insertUserDishLike(userId,-1,preference.getMenusIndex(),menuName);  //存储在关联表中初始菜单的dishId=-1

        preferenceMapper.updateById(preference);
        return R.success(preference);
    }
    private int findUnusedIndex(Set<Integer> usedMenuIds) {
        for (int i = 2; i <= MAX_MENUS; i++) {
            if (!usedMenuIds.contains(i))
                return i;
        }
        return -1; // No available index found
    }

    @Override
    public void shareMenu() //分享
    {

    }

    //返回个人菜单信息
    @Override
    public List<Map<Integer, String>> getMenus(Integer userId) {
        List<UserDishMenu> userDishMenuList = userDishMenuMapper.selectList(null);

        List<UserDishMenu> filteredList = userDishMenuList.stream()
                .filter(menu -> menu.getUserId().equals(userId))
                .toList();

        List<Map<Integer, String>> menuList = new ArrayList<>();
        for (UserDishMenu menu : filteredList) {
            Map<Integer, String> menuMap = new HashMap<>();
            menuMap.put(menu.getMenuId(), menu.getMenuName());
            menuList.add(menuMap);
        }
        //降序排列
        menuList.sort(Comparator.comparing(entry -> entry.keySet().iterator().next()));

        return menuList;
    }
    //删除菜单
    @Override
    public void deleteMenu(Integer userId, Integer menuId) {
        userDishMenuMapper.deleteByUserIdAndMenuId(userId, menuId);//关联表中删除菜单
    }
    //将菜加入黑名单
    @Override
    public void addToDisLkeMenu(Integer userId,Integer dishId) {
        insertUserDishLike(userId, -1, 0, "黑名单");
        insertUserDishLike(userId, dishId, 0, "黑名单");
    }

    @Override
    public void addToLkeMenu(Integer userId, Integer dishId) {
        insertUserDishLike(userId, -1, 1, "我喜欢的菜");
        insertUserDishLike(userId, dishId, 1, "我喜欢的菜");
    }

    @Override
    public void addToSelectMenu(Integer userId,Integer menuId,Integer dishId){
        String menuName=userDishMenuMapper.selectMenuNameByUserIdAndMenuId(userId, menuId);
        insertUserDishLike(userId,dishId,menuId,menuName);
    }
    /*
    待完成任务：得到菜单图片：菜单一号菜品的图片  ps:在insertUserDishLike中完成
     */
    @Override
    public String getMenuUrl(Integer userId, Integer menuId){
        return dishMapper.getDishById(userDishMenuMapper.selectDishesByUserIdAndMenuId(userId,menuId).getFirst()).getImage();
    }
}
