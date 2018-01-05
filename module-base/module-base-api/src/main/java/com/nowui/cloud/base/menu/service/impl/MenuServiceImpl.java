package com.nowui.cloud.base.menu.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.menu.entity.Menu;
import com.nowui.cloud.base.menu.mapper.MenuMapper;
import com.nowui.cloud.base.menu.service.MenuService;
import com.nowui.cloud.constant.Constant;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public Integer adminCount(String appId, String menuName) {
    	Integer count = 0;
    	
    	if (Util.isNullOrEmpty(menuName)) {
    		count = count(
                    new BaseWrapper<Menu>()
                            .eq(Menu.APP_ID, appId)
                            .eq(Menu.MENU_PARENT_ID, "")
                            .eq(Menu.SYSTEM_STATUS, true)
                    );
		}else {
			count = count(
                    new BaseWrapper<Menu>()
                            .eq(Menu.APP_ID, appId)
                            .likeAllowEmpty(Menu.MENU_NAME, menuName)
                            .eq(Menu.SYSTEM_STATUS, true)
                    );
		}
        
        return count;
    }

    @Override
    public List<Menu> adminList(String appId, String menuName, Integer m, Integer n) {
        List<Menu> menuList = list(
                new BaseWrapper<Menu>()
                        .eq(Menu.APP_ID, appId)
                        .likeAllowEmpty(Menu.MENU_NAME, menuName)
                        .eq(Menu.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Menu.MENU_SORT)),
                m,
                n
        );
        return menuList;
    }

	@Override
	public List<Map<String, Object>> adminTreeList(String appId, String menuName, Integer m, Integer n) {
		List<Menu> topList = list(
                new BaseWrapper<Menu>()
                        .eq(Menu.APP_ID, appId)
                        .eq(Menu.MENU_PARENT_ID, "")
                        .like(Menu.MENU_NAME, menuName)
                        .eq(Menu.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(Menu.MENU_SORT))
                ,m
                ,n
        );
		//测试
		for (Menu menu : topList) {
			System.out.println("测试:menu :"+menu);
		}
        List<Menu> childrenList = list(
                new BaseWrapper<Menu>()
                .eq(Menu.APP_ID, appId)
                .ne(Menu.MENU_PARENT_ID, "")
                .eq(Menu.SYSTEM_STATUS, true)
                .orderAsc(Arrays.asList(Menu.MENU_SORT))
        );
       
        List<Map<String, Object>> list = new ArrayList<>();
        
        for (Menu menu : topList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Menu.MENU_ID, menu.getMenuId());
            map.put(Menu.MENU_NAME, menu.getMenuName());
            map.put(Menu.MENU_SORT, menu.getMenuSort());
            map.put(Menu.MENU_URL, menu.getMenuUrl());
            map.put(Constant.CHILDREN, getChildren(childrenList, menu.getMenuId(), Menu.MENU_SORT ,Menu.MENU_URL));
            list.add(map);
        }
        return list;
	}
	
	/**
     * 递归遍历生成树形结构数据
     * @param articleCategoryList
     * @param articleCategoryParentId
     * @param keys
     * @return
     */
    private List<Map<String, Object>> getChildren(List<Menu> menuList, String menuParentId, String... keys) {
        List<Map<String, Object>> list = new ArrayList<>();
        
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                if (menuParentId.equals(menu.getMenuParentId())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(Menu.MENU_ID, menu.getMenuId());
                    map.put(Menu.MENU_NAME, menu.getMenuName());
                    
                    for (String key : keys) {
                        map.put(key, menu.get(key)); 
                    }
                    
                    List<Map<String, Object>> childrenList = getChildren(menuList, menu.getMenuId(), keys);
                    
                    map.put(Constant.CHILDREN, childrenList);
                    
                    list.add(map);
                }
            }
        }
        
        return list;
    }

}