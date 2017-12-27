package com.nowui.cloud.base.app.service;

import java.util.List;

import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.rpc.AppConfigCategoryRpc;
import com.nowui.cloud.service.BaseService;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
public interface AppConfigCategoryService extends BaseService<AppConfigCategory>, AppConfigCategoryRpc {
    
    /**
     * 应用配置分类统计
     *
     * @param appId 应用编号
     * @param configCategoryName 名称
     * @param configCategoryCode 编码
     * @return Integer 应用配置分类数量
     */
    Integer adminCount(String appId, String configCategoryName, String configCategoryCode);

    /**
     * 应用配置分类列表
     *
     * @param appId 应用编号
     * @param configCategoryName 名称
     * @param configCategoryCode 编码
     * @return List<AppConfigCategory> 应用列表
     */
    List<AppConfigCategory> adminList(String appId, String configCategoryName, String configCategoryCode, Integer m, Integer n);


}
