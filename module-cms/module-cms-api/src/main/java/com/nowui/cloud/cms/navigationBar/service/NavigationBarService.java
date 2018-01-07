package com.nowui.cloud.cms.navigationBar.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.navigationBar.entity.NavigationBar;

import java.util.List;

/**
 * 导航栏业务接口
 *
 * @author shawn
 *
 * 2017-12-31
 */
public interface NavigationBarService extends BaseService<NavigationBar> {

    /**
     * 导航栏统计
     *
     * @param appId 应用编号
     * @param navigationBarCategoryCode 导航栏分类编码
     * @param navigationBarCode 导航栏编码
     * @param navigationBarName 导航栏名称
     * @return Integer 导航栏统计
     */
    Integer adminCount(String appId, String navigationBarCategoryCode, String navigationBarCode, String navigationBarName);

    /**
     * 导航栏列表
     *
     * @param appId 应用编号
     * @param navigationBarCategoryCode 导航栏分类编码
     * @param navigationBarCode 导航栏编码
     * @param navigationBarName 导航栏名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<NavigationBar> 导航栏列表
     */
    List<NavigationBar> adminList(String appId, String navigationBarCategoryCode, String navigationBarCode, String navigationBarName, Integer m, Integer n);
}
