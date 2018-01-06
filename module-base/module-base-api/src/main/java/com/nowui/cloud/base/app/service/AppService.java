package com.nowui.cloud.base.app.service;

import java.util.List;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.app.rpc.AppRpc;
import com.nowui.cloud.service.BaseService;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
public interface AppService extends BaseService<App>, AppRpc {
    
    /**
     * 应用统计
     *
     * @param appName 应用名称
     * @return Integer 应用数量
     */
    Integer adminCount(String appName);

    /**
     * 应用列表
     *
     * @param appName 应用名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<App> 应用列表
     */
    List<App> adminList(String appName, Integer pageIndex, Integer pageSize);
    
    /**
     * 验证名称唯一性
     * @param appName
     * @return true 重复 false 不重复
     */
    Boolean checkName(String appName);
    
    /**
     * 验证名称唯一性
     * @param appId
     * @param appName
     * @return true 重复 false 不重复
     */
    Boolean checkName(String appId, String appName);

}
