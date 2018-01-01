package com.nowui.cloud.base.menu.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.menu.entity.Menu;

import java.util.List;

/**
 * 菜单业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 菜单统计
     *
     * @param appId 应用编号
     * @param menuParentId 父级ID
     * @param menuName 名称
     * @param menuImage 图片
     * @param menuUrl 地址
     * @return Integer 菜单统计
     */
    Integer adminCount(String appId, String menuParentId, String menuName, String menuImage, String menuUrl);

    /**
     * 菜单列表
     *
     * @param appId 应用编号
     * @param menuParentId 父级ID
     * @param menuName 名称
     * @param menuImage 图片
     * @param menuUrl 地址
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Menu> 菜单列表
     */
    List<Menu> adminList(String appId, String menuParentId, String menuName, String menuImage, String menuUrl, Integer m, Integer n);
}
