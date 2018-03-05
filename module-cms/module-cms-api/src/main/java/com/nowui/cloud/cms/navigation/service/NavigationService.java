package com.nowui.cloud.cms.navigation.service;
import java.util.List;

import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.view.NavigationView;
import com.nowui.cloud.service.SuperService;

/**
 * 导航栏业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface NavigationService extends SuperService<Navigation, NavigationView> {

    /**
     * 导航栏统计
     *
     * @param appId 应用编号
     * @param navigationCategoryCode 导航栏分类编码
     * @param navigationCode 导航栏编码
     * @param navigationName 导航栏名称
     * @return Integer 导航栏统计
     */
    Integer countForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName);

    /**
     * 导航栏列表
     *
     * @param appId 应用编号
     * @param navigationCategoryCode 导航栏分类编码
     * @param navigationCode 导航栏编码
     * @param navigationName 导航栏名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<NavigationView> 导航栏列表
     */
    List<NavigationView> listForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName, Integer m, Integer n);
    
    /**
     * 导航栏列表
     * 
     * @param appId 应用编号
     * @return List<Navigation> 导航栏列表
     */
    List<NavigationView> mobileList(String appId, String navigationCategoryCode);
    
    /**
     * 根据导航栏分类编码查询导航栏列表
     * 
     * @param appId 应用编号
     * @param navigationCategoryCode 导航栏分类编码
     * @return List<Navigation> 导航栏列表
     */
    List<NavigationView> listByCategoryCode(String appId, String navigationCategoryCode);
}
