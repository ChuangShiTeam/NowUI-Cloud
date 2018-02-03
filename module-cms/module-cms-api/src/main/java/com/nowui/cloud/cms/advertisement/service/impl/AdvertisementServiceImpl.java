package com.nowui.cloud.cms.advertisement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.mapper.AdvertisementMapper;
import com.nowui.cloud.cms.advertisement.repository.AdvertisementRepository;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 广告业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class AdvertisementServiceImpl extends SuperServiceImpl<AdvertisementMapper, Advertisement, AdvertisementRepository, AdvertisementView> implements AdvertisementService {
    
    @Override
    public Integer countForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle) {
        Integer count = count(
                new BaseWrapper<Advertisement>()
                        .eq(Advertisement.APP_ID, appId)
                        .like(Advertisement.ADEVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
                        .like(Advertisement.ADEVERTISEMENT_TITLE, advertisementTitle)
                        .eq(Advertisement.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Advertisement> listForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle, Integer m,
            Integer n) {
        List<Advertisement> advertisementList = list(
                new BaseWrapper<Advertisement>()
                        .eq(Advertisement.APP_ID, appId)
                        .like(Advertisement.ADEVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
                        .like(Advertisement.ADEVERTISEMENT_TITLE, advertisementTitle)
                        .eq(Advertisement.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(Advertisement.ADEVERTISEMENT_SORT))
                ,m
                ,n
        );
        
        return advertisementList;
    }

	@Override
	public List<Advertisement> mobileList(String appId, String advertisementCategoryCode) {
		List<Advertisement> bannerList = list(new BaseWrapper<Advertisement>()
				.eq(Advertisement.APP_ID, appId)
				.eq(Advertisement.ADEVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
				.eq(Advertisement.ADEVERTISEMENT_IS_EFFICIENT, false)
				.eq(Advertisement.SYSTEM_STATUS, true)
				.orderAsc(Arrays.asList(Advertisement.ADEVERTISEMENT_SORT))
			);

		return bannerList;
	}

    @Override
    public List<Advertisement> listByCategoryCode(String appId, String advertisementCategoryCode) {
        List<Advertisement> bannerList = list(new BaseWrapper<Advertisement>()
                .eq(Advertisement.APP_ID, appId)
                .eq(Advertisement.ADEVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
                .eq(Advertisement.ADEVERTISEMENT_IS_EFFICIENT, false)
                .eq(Advertisement.SYSTEM_STATUS, true)
                .orderAsc(Arrays.asList(Advertisement.ADEVERTISEMENT_SORT))
            );

        return bannerList;
    }
    
}
