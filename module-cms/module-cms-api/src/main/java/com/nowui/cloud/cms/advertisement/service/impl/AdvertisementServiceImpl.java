package com.nowui.cloud.cms.advertisement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.mapper.AdvertisementMapper;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 广告业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class AdvertisementServiceImpl extends BaseServiceImpl<AdvertisementMapper, Advertisement> implements AdvertisementService {

    @Override
    public Integer adminCount(String appId, String advertisementCategoryCode, String advertisementTitle) {
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
    public List<Advertisement> adminList(String appId, String advertisementCategoryCode, String advertisementTitle, Integer m,
            Integer n) {
        List<Advertisement> advertisementList = list(
                new BaseWrapper<Advertisement>()
                        .eq(Advertisement.APP_ID, appId)
                        .like(Advertisement.ADEVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
                        .like(Advertisement.ADEVERTISEMENT_TITLE, advertisementTitle)
                        .eq(Advertisement.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Advertisement.SYSTEM_CREATE_TIME))
                ,m
                ,n
        );
        
        return advertisementList;
    }
    
}
