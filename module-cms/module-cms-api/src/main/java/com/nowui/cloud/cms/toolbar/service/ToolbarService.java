package com.nowui.cloud.cms.toolbar.service;

import java.util.List;

import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.service.BaseService;

/**
 * 工具栏业务接口
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
public interface ToolbarService extends BaseService<Toolbar>{
    
    /**
     * 工具栏统计
     *
     * @param appId 应用编号
     * @param toolbarName 工具栏名称
     * @return Integer 工具栏数量
     */
    Integer adminCount(String appId, String toolbarName);

    /**
     * 工具栏列表
     *
     * @param appId 应用编号
     * @param articleName 工具栏名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Toolbar> 工具栏列表
     */
    List<Toolbar> adminList(String appId, String toolbarName, Integer m, Integer n);

    /**
     * 工具栏列表
     * @param appId
     * @return List<Toolbar> 工具栏列表
     */
    List<Toolbar> mobileList(String appId);
}
