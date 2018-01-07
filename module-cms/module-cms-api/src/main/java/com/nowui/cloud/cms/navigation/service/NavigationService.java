package com.nowui.cloud.cms.navigation.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.navigation.entity.Navigation;

import java.util.List;

/**
 * 导航栏业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface NavigationService extends BaseService<Navigation> {

    /**
     * 导航栏统计
     *
     * @param appId 应用编号
     * @param navigationCategoryCode 导航栏分类编码
     * @param navigationCode 导航栏编码
     * @param navigationName 导航栏名称
     * @return Integer 导航栏统计
     */
    Integer adminCount(String appId, String navigationCategoryCode, String navigationCode, String navigationName);

    /**
     * 导航栏列表
     *
     * @param appId 应用编号
     * @param navigationCategoryCode 导航栏分类编码
     * @param navigationCode 导航栏编码
     * @param navigationName 导航栏名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Navigation> 导航栏列表
     */
    List<Navigation> adminList(String appId, String navigationCategoryCode, String navigationCode, String navigationName, Integer m, Integer n);
    
    /**
     * 导航栏
     * 
     * @param appId 应用编号
     * @return List<Navigation> 导航栏列表
     */
    List<Navigation> mobileList(String appId, String navigationCategoryCode);
}
