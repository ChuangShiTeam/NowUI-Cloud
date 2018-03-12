package com.nowui.cloud.cms.toolbar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.cms.toolbar.mapper.ToolbarMapper;
import com.nowui.cloud.cms.toolbar.repository.ToolbarRepository;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.cms.toolbar.view.ToolbarView;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 工具栏业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ToolbarServiceImpl extends BaseServiceImpl<ToolbarMapper, Toolbar, ToolbarRepository, ToolbarView> implements ToolbarService {

    @Override
    public Integer countForAdmin(String appId, String toolbarName) {
        Criteria criteria = Criteria.where(ToolbarView.APP_ID).is(appId)
                .and(ToolbarView.TOOLBAR_NAME).regex(".*?" + toolbarName + ".*")
                .and(ToolbarView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<ToolbarView> listForAdmin(String appId, String toolbarName, Integer m, Integer n) {
        Criteria criteria = Criteria.where(ToolbarView.APP_ID).is(appId)
                .and(ToolbarView.TOOLBAR_NAME).regex(".*?" + toolbarName + ".*")
                .and(ToolbarView.SYSTEM_STATUS).is(true);
        
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, ToolbarView.TOOLBAR_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ToolbarView> toolbarViews = list(query, sort, m, n);
        
        return toolbarViews;
    }

	@Override
	public List<ToolbarView> mobileList(String appId) {
	    Criteria criteria = Criteria.where(ToolbarView.APP_ID).is(appId)
                .and(ToolbarView.SYSTEM_STATUS).is(true);
        
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, ToolbarView.TOOLBAR_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ToolbarView> toolbarViews = list(query, sort);
        
        return toolbarViews;
	}
    
    

}
