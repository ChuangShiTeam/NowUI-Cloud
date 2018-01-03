package com.nowui.cloud.cms.toolbar.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.advertisement.entity.Advertisement;
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

    @Autowired
    private FileRpc fileRpc;
    
    @Override
    public Integer adminCount(String appId, String toolbarName) {
        Integer count = count(
                new BaseWrapper<Toolbar>()
                        .eq(Toolbar.APP_ID, appId)
                        .likeAllowEmpty(Toolbar.TOOLBAR_NAME, toolbarName)
                        .eq(Toolbar.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Toolbar> adminList(String appId, String toolbarName, Integer m, Integer n) {
        List<Toolbar> toolbarList = list(
                new BaseWrapper<Toolbar>()
                        .eq(Toolbar.APP_ID, appId)
                        .likeAllowEmpty(Toolbar.TOOLBAR_NAME, toolbarName)
                        .eq(Toolbar.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(Toolbar.TOOLBAR_SORT))
                ,m
                ,n
        );
        
        //查询工具栏图片
        for (Toolbar toolbar : toolbarList) {
            File file = fileRpc.find(toolbar.getToolbarImage());
            file.keep(File.FILE_ID, File.FILE_PATH);
            toolbar.put(Toolbar.TOOLBAR_IMAGE, file);
        }
        
        return toolbarList;
    }

	@Override
	public List<Toolbar> mobileList(String appId) {
		List<Toolbar> resultList = list(
			new BaseWrapper<Toolbar>()
			.eq(Toolbar.APP_ID, appId)
			.eq(Toolbar.SYSTEM_STATUS, true)
			.orderDesc(Arrays.asList(Toolbar.TOOLBAR_SORT))
			);
		
		return resultList;
	}
    
    

}
