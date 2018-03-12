package com.nowui.cloud.base.menu.service;
import com.nowui.cloud.base.menu.view.MenuView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.menu.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * 菜单业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface MenuService extends BaseService<Menu,MenuView> {

    /**
     * 菜单统计
     *
     * @param appId 应用编号
     * @param menuName 名称
     * @return Integer 菜单统计
     */
    Integer countForAdmin(String appId, String menuName);

    /**
     * 菜单列表
     *
     * @param appId 应用编号
     * @param menuName 名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Menu> 菜单列表
     */
    List<Menu> listForAdmin(String appId, String menuName, Integer pageIndex, Integer pageSize);
    
    /**
     * 菜单属性列表
     * @param appId 应用编号
     * @param menuName 菜单名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Map<String, Object>> 菜单属性列表
     */
    List<Map<String, Object>> treeListForAdmin(String appId, String menuName, Integer pageIndex, Integer pageSize);
    
}
