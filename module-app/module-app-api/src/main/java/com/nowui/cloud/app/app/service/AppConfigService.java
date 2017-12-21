package com.nowui.cloud.app.app.service;

import java.util.List;

import com.nowui.cloud.app.app.entity.AppConfig;
import com.nowui.cloud.app.app.rpc.AppConfigRpc;
import com.nowui.cloud.service.BaseService;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
public interface AppConfigService extends BaseService<AppConfig>, AppConfigRpc {
    
    /**
     * 应用配置统计
     *
     * @param appId 应用编号
     * @param configCategoryId 应用配置分类编号
     * @param configKey 键
     * @param configIsDisabled 是否禁用
     * @return Integer 应用配置数量
     */
    Integer adminCount(String appId, String configCategoryId, String configKey, Boolean configIsDisabled);

    /**
     * 应用配置列表
     *
     * @param appId 应用编号
     * @param configCategoryId 应用配置分类编号
     * @param configKey 键
     * @param configIsDisabled 是否禁用
     * @return List<AppConfig> 应用列表
     */
    List<AppConfig> adminList(String appId, String configCategoryId, String configKey, Boolean configIsDisabled, Integer m, Integer n);

}
