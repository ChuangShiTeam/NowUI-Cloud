package com.nowui.cloud.member.member.service;
import java.util.List;

import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.service.BaseService;

/**
 * 会员地址业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberAddressService extends BaseService<MemberAddress> {

    /**
     * 会员地址统计
     *
     * @param appId 应用编号
     * @param memberAddressProvince 省
     * @param memberAddressCity 市
     * @param memberAddressArea 区
     * @return Integer 会员地址统计
     */
    Integer countForAdmin(String appId, String memberAddressProvince, String memberAddressCity, String memberAddressArea);

    /**
     * 会员地址列表
     *
     * @param appId 应用编号
     * @param memberAddressProvince 省
     * @param memberAddressCity 市
     * @param memberAddressArea 区
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberAddress> 会员地址列表
     */
    List<MemberAddress> listForAdmin(String appId, String memberAddressProvince, String memberAddressCity, String memberAddressArea, Integer pageIndex, Integer pageSize);
}
