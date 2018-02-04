package com.nowui.cloud.cms.advertisement.service;

import java.util.List;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.rpc.AdvertisementRpc;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;

/**
 * 广告业务接口
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
public interface AdvertisementService extends SuperService<Advertisement, AdvertisementView> {
    
    /**
     * 广告统计
     *
     * @param appId 应用编号
     * @param advertisementCategoryCode 广告分类编码
     * @param advertisementTitle 广告标题
     * @return Integer 广告数量
     */
    Integer countForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle);

    /**
     * 广告列表
     *
     * @param appId 应用编号
     * @param advertisementCategoryCode 广告分类编码
     * @param advertisementTitle 广告标题
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Advertisement> 广告列表
     */
    List<AdvertisementView> listForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle, Integer m, Integer n);

    /**
     * 广告列表
     * @param appId 应用编号
     * @param advertisementCategoryCode 广告分类编码
     * @return List<Advertisement> 广告列表
     */
    List<Advertisement> mobileList(String appId, String advertisementCategoryCode);
    
    /**
     * 根据广告分类编码查询广告列表
     * @param appId 应用编号
     * @param advertisementCategoryCode 广告分类编码
     * @return List<Advertisement> 广告列表
     */
    List<Advertisement> listByCategoryCode(String appId, String advertisementCategoryCode);

}
