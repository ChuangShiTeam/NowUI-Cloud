package com.nowui.cloud.cms.toolbar.service;

import java.util.List;

import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.cms.toolbar.view.ToolbarView;
import com.nowui.cloud.service.SuperService;

/**
 * 工具栏业务接口
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
public interface ToolbarService extends SuperService<Toolbar, ToolbarView>{
    
    /**
     * 工具栏统计
     *
     * @param appId 应用编号
     * @param toolbarName 工具栏名称
     * @return Integer 工具栏数量
     */
    Integer countForAdmin(String appId, String toolbarName);

    /**
     * 工具栏列表
     *
     * @param appId 应用编号
     * @param articleName 工具栏名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<ToolbarView> 工具栏视图列表
     */
    List<ToolbarView> listForAdmin(String appId, String toolbarName, Integer m, Integer n);

    /**
     * 工具栏列表
     * @param appId
     * @return List<ToolbarView> 工具栏视图列表
     */
    List<ToolbarView> mobileList(String appId);
}
