package com.nowui.cloud.base.app.service;

import java.util.List;

import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.view.AppConfigView;
import com.nowui.cloud.service.SuperService;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
public interface AppConfigService extends SuperService<AppConfig, AppConfigView> {
    
    /**
     * 应用配置统计
     *
     * @param appId 应用编号
     * @param configCategoryId 应用配置分类编号
     * @param configKey 键
     * @param configIsDisabled 是否禁用
     * @return Integer 应用配置数量
     */
    Integer countForAdmin(String appId, String configCategoryId, String configKey, Boolean configIsDisabled);

    /**
     * 应用配置列表
     *
     * @param appId 应用编号
     * @param configCategoryId 应用配置分类编号
     * @param configKey 键
     * @param configIsDisabled 是否禁用
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<AppConfig> 应用配置列表
     */
    List<AppConfigView> listForAdmin(String appId, String configCategoryId, String configKey, Boolean configIsDisabled, Integer pageIndex, Integer pageSize);

    /**
     * 查询应用配置分类下的可用的应用配置列表
     * 
     * @param configCategoryId
     * @return List<AppConfig> 应用配置列表
     */
    List<AppConfigView> abledListByConfigCategoryId(String appId, String configCategoryId);
}
