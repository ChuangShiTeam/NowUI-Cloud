package com.nowui.cloud.cms.advertisement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.mapper.AdvertisementMapper;
import com.nowui.cloud.cms.advertisement.repository.AdvertisementRepository;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 广告业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class AdvertisementServiceImpl extends BaseServiceImpl<AdvertisementMapper, Advertisement, AdvertisementRepository, AdvertisementView> implements AdvertisementService {
    
    @Override
    public Integer countForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle) {
        Criteria criteria = Criteria.where(AdvertisementView.APP_ID).is(appId)
                .and(AdvertisementView.ADVERTISEMENT_CATEGORY_CODE).regex(".*?" + advertisementCategoryCode + ".*")
                .and(AdvertisementView.ADVERTISEMENT_TITLE).regex(".*?" + advertisementTitle + ".*")
                .and(AdvertisementView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }

    @Override
    public List<AdvertisementView> listForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(AdvertisementView.APP_ID).is(appId)
                .and(AdvertisementView.ADVERTISEMENT_CATEGORY_CODE).regex(".*?" + advertisementCategoryCode + ".*")
                .and(AdvertisementView.ADVERTISEMENT_TITLE).regex(".*?" + advertisementTitle + ".*")
                .and(AdvertisementView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, AdvertisementView.ADVERTISEMENT_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AdvertisementView> advertisementViewList = list(query, sort, pageIndex, pageSize);
        
        return advertisementViewList;
    }

	@Override
	public List<AdvertisementView> listByCategoryCode(String appId, String advertisementCategoryCode) {
	    Criteria criteria = Criteria.where(AdvertisementView.APP_ID).is(appId)
                .and(AdvertisementView.ADVERTISEMENT_CATEGORY_CODE).is(advertisementCategoryCode)
                .and(AdvertisementView.ADVERTISEMENT_IS_EFFICIENT).is(false)
                .and(AdvertisementView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, AdvertisementView.ADVERTISEMENT_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AdvertisementView> advertisementViewList = list(query, sort);
        
		return advertisementViewList;
	}

}
