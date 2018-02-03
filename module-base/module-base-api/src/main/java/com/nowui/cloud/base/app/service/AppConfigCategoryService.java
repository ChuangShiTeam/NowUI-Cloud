package com.nowui.cloud.base.app.service;

import java.util.List;

import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.service.SuperService;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
public interface AppConfigCategoryService extends SuperService<AppConfigCategory> {
    
    /**
     * 应用配置分类统计
     *
     * @param appId 应用编号
     * @param configCategoryName 名称
     * @param configCategoryCode 编码
     * @return Integer 应用配置分类数量
     */
    Integer countForAdmin(String appId, String configCategoryName, String configCategoryCode);

    /**
     * 应用配置分类列表
     *
     * @param appId 应用编号
     * @param configCategoryName 名称
     * @param configCategoryCode 编码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<AppConfigCategory> 应用配置分类列表
     */
    List<AppConfigCategory> listForAdmin(String appId, String configCategoryName, String configCategoryCode, Integer pageIndex, Integer pageSize);
    
    /**
     * 应用配置分类列表
     *
     * @param appId 应用编号
     * @return List<AppConfigCategory> 应用配置分类列表
     */
    List<AppConfigCategory> appList(String appId);
    
    /**
     * 根据配置分类编码查询应用配置分类信息
     * 
     * @param appId 应用编号
     * @param configCategoryCode 配置分类编码
     * @return 应用配置分类
     */
    AppConfigCategory findByConfigCategoryCode(String appId, String configCategoryCode);

}
