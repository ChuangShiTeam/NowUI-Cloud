package com.nowui.cloud.cms.advertisement.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.mapper.AdvertisementMapper;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 广告业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class AdvertisementServiceImpl extends BaseServiceImpl<AdvertisementMapper, Advertisement> implements AdvertisementService {

    @Autowired
    private FileRpc fileRpc;
    
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
                        .orderDesc(Arrays.asList(Advertisement.SYSTEM_CREATE_TIME))
                ,m
                ,n
        );
        
        //查询广告图片
        for (Advertisement advertisement : advertisementList) {
            File file = fileRpc.find(advertisement.getAdvertisementImage());
            file.keep(File.FILE_ID, File.FILE_PATH);
            advertisement.put(Advertisement.ADEVERTISEMENT_IMAGE, file);
        }
        return advertisementList;
    }

	@Override
	public List<Advertisement> mobileList(String appId, String advertisementCategoryCode) {
		List<Advertisement> bannerList = list(new BaseWrapper<Advertisement>()
				.eq(Advertisement.APP_ID, appId)
				.eq(Advertisement.ADEVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
				.eq(Advertisement.ADEVERTISEMENT_IS_EFFICIENT, true)
				.eq(Advertisement.SYSTEM_STATUS, true)
				.orderDesc(Arrays.asList(Advertisement.ADEVERTISEMENT_SORT))
			);

		return bannerList;
	}
    
}
