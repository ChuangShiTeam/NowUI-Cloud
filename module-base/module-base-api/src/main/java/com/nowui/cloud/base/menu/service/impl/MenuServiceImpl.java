package com.nowui.cloud.base.menu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.menu.entity.Menu;
import com.nowui.cloud.base.menu.mapper.MenuMapper;
import com.nowui.cloud.base.menu.repository.MenuRepository;
import com.nowui.cloud.base.menu.service.MenuService;
import com.nowui.cloud.base.menu.view.MenuView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 菜单业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu, MenuRepository, MenuView> implements MenuService {

    @Override
    public Integer countForAdmin(String appId, String menuName) {
    	Integer count = 0;
    	
    	if (Util.isNullOrEmpty(menuName)) {
    	    Criteria criteria = Criteria.where(MenuView.APP_ID).is(appId)
                    .and(MenuView.MENU_PARENT_ID).is("")
                    .and(MenuView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            count = count(query);
		}else {
		    Criteria criteria = Criteria.where(MenuView.APP_ID).is(appId)
                    .and(MenuView.MENU_NAME).regex(".*?" + menuName + ".*")
                    .and(MenuView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            count = count(query);
		}
        
        return count;
    }

    @Override
    public List<MenuView> listForAdmin(String appId, String menuName, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(MenuView.APP_ID).is(appId)
                .and(MenuView.MENU_NAME).regex(".*?" + menuName + ".*")
                .and(MenuView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, MenuView.MENU_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
        
        return list(query, sort, pageIndex, pageSize);
    }

	@Override
	public List<Map<String, Object>> treeListForAdmin(String appId, Integer pageIndex, Integer pageSize) {
	    Criteria criteria = Criteria.where(MenuView.APP_ID).is(appId)
                .and(MenuView.MENU_PARENT_ID).is("")
                .and(MenuView.SYSTEM_STATUS).is(true);

	    List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, MenuView.MENU_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
		List<MenuView> topList = list(query, sort, pageIndex, pageSize);
		
		
		Criteria criteria1 = Criteria.where(MenuView.APP_ID).is(appId)
                .and(MenuView.MENU_PARENT_ID).ne("")
                .and(MenuView.SYSTEM_STATUS).is(true);
		Query query1 = new Query(criteria1);
		List<MenuView> childrenList = list(query1, sort);
		
        List<Map<String, Object>> list = new ArrayList<>();
        
        for (MenuView menuView : topList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(MenuView.MENU_ID, menuView.getMenuId());
            map.put(MenuView.MENU_NAME, menuView.getMenuName());
            map.put(MenuView.MENU_SORT, menuView.getMenuSort());
            map.put(MenuView.MENU_URL, menuView.getMenuUrl());
            map.put(Constant.CHILDREN, getChildren(childrenList, menuView.getMenuId()));
            list.add(map);
        }
        return list;
	}
	
	/**
     * 递归遍历生成树形结构数据
     * @param menuList
     * @param menuParentId
     * @param keys
     * @return
     */
    private List<Map<String, Object>> getChildren(List<MenuView> menuViewList, String menuParentId) {
        List<Map<String, Object>> list = new ArrayList<>();
        
        if (menuViewList != null && menuViewList.size() > 0) {
            for (MenuView menuView : menuViewList) {
                if (menuParentId.equals(menuView.getMenuParentId())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(MenuView.MENU_ID, menuView.getMenuId());
                    map.put(MenuView.MENU_NAME, menuView.getMenuName());
                    map.put(MenuView.MENU_SORT, menuView.getMenuSort());
                    map.put(MenuView.MENU_URL, menuView.getMenuUrl());
                    
                    List<Map<String, Object>> childrenList = getChildren(menuViewList, menuView.getMenuId());
                    
                    map.put(Constant.CHILDREN, childrenList);
                    
                    list.add(map);
                }
            }
        }
        
        return list;
    }

}