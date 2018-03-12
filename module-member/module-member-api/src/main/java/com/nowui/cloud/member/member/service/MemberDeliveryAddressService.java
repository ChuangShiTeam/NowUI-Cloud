package com.nowui.cloud.member.member.service;
import com.nowui.cloud.member.member.view.MemberDeliveryAddressView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.member.member.entity.MemberDeliveryAddress;
import com.nowui.cloud.service.BaseService;

import java.util.List;

/**
 * 会员收货地址业务接口
 *
 * @author xinqing
 *
 * 2018-01-14
 */
public interface MemberDeliveryAddressService extends BaseService<MemberDeliveryAddress,MemberDeliveryAddressView> {

    /**
     * 会员收货地址统计
     *
     * @param appId 应用编号
     * @param memberDeliveryAddressName 姓名
     * @param memberDeliveryAddressPhone 手机号
     * @return Integer 会员收货地址统计
     */
    Integer countForAdmin(String appId, String memberDeliveryAddressName, String memberDeliveryAddressPhone);

    /**
     * 会员收货地址列表
     *
     * @param appId 应用编号
     * @param memberDeliveryAddressName 姓名
     * @param memberDeliveryAddressPhone 手机号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberDeliveryAddress> 会员收货地址列表
     */
    List<MemberDeliveryAddress> listForAdmin(String appId, String memberDeliveryAddressName, String memberDeliveryAddressPhone, Integer pageIndex, Integer pageSize);
}
