package com.nowui.cloud.cms.toolbar.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.cms.toolbar.mapper.ToolbarMapper;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 工具栏业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ToolbarServiceImpl extends BaseServiceImpl<ToolbarMapper, Toolbar> implements ToolbarService {

    @Override
    public Integer countForAdmin(String appId, String toolbarName) {
        Integer count = count(
                new BaseWrapper<Toolbar>()
                        .eq(Toolbar.APP_ID, appId)
                        .likeAllowEmpty(Toolbar.TOOLBAR_NAME, toolbarName)
                        .eq(Toolbar.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Toolbar> listForAdmin(String appId, String toolbarName, Integer m, Integer n) {
        List<Toolbar> toolbarList = list(
                new BaseWrapper<Toolbar>()
                        .eq(Toolbar.APP_ID, appId)
                        .likeAllowEmpty(Toolbar.TOOLBAR_NAME, toolbarName)
                        .eq(Toolbar.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(Toolbar.TOOLBAR_SORT))
                ,m
                ,n
        );
        
        return toolbarList;
    }

	@Override
	public List<Toolbar> mobileList(String appId) {
		List<Toolbar> resultList = list(
			new BaseWrapper<Toolbar>()
    			.eq(Toolbar.APP_ID, appId)
    			.eq(Toolbar.SYSTEM_STATUS, true)
    			.orderAsc(Arrays.asList(Toolbar.TOOLBAR_SORT))
		);
		
		return resultList;
	}
    
    

}
