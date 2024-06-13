package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.common.R;
import com.ccb.mapper.DishMapper;
import com.ccb.mapper.PreferenceMapper;
import com.ccb.mapper.UserDishMenuMapper;
import com.ccb.mapper.UserMapper;
import com.ccb.model.pojo.Preference;
import com.ccb.model.pojo.UserDishMenu;
import com.ccb.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements PreferenceService {
    @Autowired
    PreferenceMapper preferenceMapper;
    @Autowired
    UserDishMenuMapper userDishMenuMapper;
    @Autowired
    DishMapper dishMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void changeMenuName(Integer userId, Integer menuId,String menuName){
        userDishMenuMapper.updateMenuNameByUserIdAndMenuId(userId,menuId,menuName);
    }

    @Override
    public void deleteDish(Integer userId, Integer menuId, Integer dishId) {
        QueryWrapper<UserDishMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("menu_id", menuId)
                .eq("dish_id", dishId);
        // 执行删除操作
        int deleteCount = userDishMenuMapper.delete(queryWrapper);
    }

    public static final Integer MAX_MENUS = 10000;
    public void insertUserDishLike(Integer userId, Integer dishId, Integer menuId,String menuName) {
        // 创建原始记录
        UserDishMenu userDishMenu = new UserDishMenu();
        userDishMenu.setUserId(userId);
        userDishMenu.setDishId(dishId);
        userDishMenu.setMenuId(menuId);
        userDishMenu.setMenuName(menuName);
        userDishMenu.setCopy(false); // 原始记录标记为 false
        String menuUrl = dishMapper.selectImageById(dishId);
        userDishMenu.setMenuUrl(menuUrl);

        // 插入原始记录
        userDishMenuMapper.insert(userDishMenu);

        // 创建副本记录
        UserDishMenu copy = new UserDishMenu();
        copy.setUserId(-1);
        copy.setDishId(dishId);
        copy.setMenuId(menuId);
        copy.setMenuName(menuName);
        copy.setMenuUrl(menuUrl);
        copy.setCopy(true); // 副本记录标记为 true

        // 插入副本记录
        userDishMenuMapper.insert(copy);
    }
    public Integer getIdByMenu(Integer userId,Integer menuId){
        QueryWrapper<UserDishMenu> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId)
                    .eq("menu_id",menuId);
        return userDishMenuMapper.selectList(queryWrapper).getFirst().getId();
    }
    public Preference getByUserId(Integer userId) {
        return preferenceMapper.selectByUserId(userId);
    }
    //获得一个菜单里的菜
    public List<Integer> getMenuDishes(Integer userId, Integer menuId) {
        return userDishMenuMapper.selectDishesByUserIdAndMenuId(userId, menuId);
    }

    //加新菜单，菜单编号顺序从2开始递增，但并无实际意义（删除可以删除其中任意一个）上限不超过10000(MAX_MENUS)
    public R creatMenu(Integer userId, String menuName) {
        // Fetch existing menuIds for the user
        List<Map<Integer, String>> existingMenus = getMenus(userId);
        Set<Integer> existingMenuIds = new HashSet<>();
        for (Map<Integer, String> menu : existingMenus) {
            existingMenuIds.addAll(menu.keySet());
        }

        // Generate a random menuId that does not conflict with existing menuIds
        Random rand = new Random();
        int randomMenuId;
        do {
            randomMenuId = rand.nextInt(10000 - 2 + 1) + 2; // 生成2到10000之间的随机数
        } while (existingMenuIds.contains(randomMenuId));

        // Insert the new menu
        insertUserDishLike(userId, -1, randomMenuId, menuName); // 存储在关联表中初始菜单的dishId=-1

        return R.success();
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
                .filter(menu -> menu.getUserId() != null && menu.getUserId().equals(userId))
                .filter(menu -> menu.getMenuId() != null && menu.getMenuId() > 1)
                .sorted(Comparator.comparing(UserDishMenu::getId).reversed())
                .collect(Collectors.toCollection(ArrayList::new)); // Collect to a mutable list

        List<Map<Integer, String>> menuList = new ArrayList<>();
        for (UserDishMenu menu : filteredList) {
            Map<Integer, String> menuMap = new HashMap<>();
            menuMap.put(menu.getMenuId(), menu.getMenuName());
            menuList.add(menuMap);
        }

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
        if(menuName==null) {
            if (menuId == 1) {
                addToLkeMenu(userId, dishId);
                menuName="我喜欢的菜";
            }
            else if(menuId==0)
                addToDisLkeMenu(userId,dishId);
                menuName="黑名单";
        }
        insertUserDishLike(userId,dishId,menuId,menuName);
    }
    /*
    待完成任务：得到菜单图片：菜单一号菜品的图片  ps:在insertUserDishLike中完成
     */
    @Override
    public String getMenuUrl(Integer userId, Integer menuId){
        return dishMapper.selectImageById(userDishMenuMapper.selectDishesByUserIdAndMenuId(userId,menuId).getFirst());
    }

    public Integer getMenuIdByMenuName(int userId,String menuName){
        QueryWrapper<UserDishMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("menu_name", menuName);
        return userDishMenuMapper.selectList(queryWrapper).getFirst().getMenuId();
    }
    //1、克隆菜单
    public void cloneMenu(Integer curUserId, Integer cloneUserId, Integer menuId){
       List<Integer>cloneDishesId =  getMenuDishes(cloneUserId,menuId);

       creatMenu(curUserId,userDishMenuMapper.selectMenuNameByUserIdAndMenuId(cloneUserId,menuId));

       String menuName=userDishMenuMapper.selectMenuNameByUserIdAndMenuId(cloneUserId,menuId);
       for(Integer dishId:cloneDishesId){
            addToSelectMenu(curUserId,getMenuIdByMenuName(curUserId,menuName),dishId);
       }
    }
    //2、检索最新加菜的菜单  在creatMenu中实现
    //3、userId=-1的菜单池
}
