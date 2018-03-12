package com.nowui.cloud.member.member.service.impl;

import com.nowui.cloud.member.member.repository.MemberDeliveryAddressRepository;
import com.nowui.cloud.member.member.view.MemberDeliveryAddressView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.member.member.entity.MemberDeliveryAddress;
import com.nowui.cloud.member.member.mapper.MemberDeliveryAddressMapper;
import com.nowui.cloud.member.member.service.MemberDeliveryAddressService;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 会员收货地址业务实现
 *
 * @author xinqing
 *
 * 2018-01-14
 */
@Service
public class MemberDeliveryAddressServiceImpl extends BaseServiceImpl<MemberDeliveryAddressMapper, MemberDeliveryAddress,MemberDeliveryAddressRepository,MemberDeliveryAddressView> implements MemberDeliveryAddressService {

    @Override
    public Integer countForAdmin(String appId, String memberDeliveryAddressName, String memberDeliveryAddressPhone) {
        Integer count = count(
                new BaseWrapper<MemberDeliveryAddress>()
                        .eq(MemberDeliveryAddress.APP_ID, appId)
                        .likeAllowEmpty(MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_NAME, memberDeliveryAddressName)
                        .likeAllowEmpty(MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PHONE, memberDeliveryAddressPhone)
                        .eq(MemberDeliveryAddress.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberDeliveryAddress> listForAdmin(String appId, String memberDeliveryAddressName, String memberDeliveryAddressPhone, Integer pageIndex, Integer pageSize) {
        List<MemberDeliveryAddress> memberDeliveryAddressList = list(
                new BaseWrapper<MemberDeliveryAddress>()
                        .eq(MemberDeliveryAddress.APP_ID, appId)
                        .likeAllowEmpty(MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_NAME, memberDeliveryAddressName)
                        .likeAllowEmpty(MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PHONE, memberDeliveryAddressPhone)
                        .eq(MemberDeliveryAddress.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberDeliveryAddress.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberDeliveryAddressList;
    }

}