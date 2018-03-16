package com.nowui.cloud.member.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberDeliveryAddress;
import com.nowui.cloud.member.member.mapper.MemberDeliveryAddressMapper;
import com.nowui.cloud.member.member.repository.MemberDeliveryAddressRepository;
import com.nowui.cloud.member.member.service.MemberDeliveryAddressService;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;
import com.nowui.cloud.member.member.view.MemberDeliveryAddressView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

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
        Criteria criteria = Criteria.where(MemberDeliveryAddressView.APP_ID).is(appId)
                .and(MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME).regex(".*?" + memberDeliveryAddressName + ".*")
                .and(MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE).regex(".*?" + memberDeliveryAddressPhone + ".*")
                .and(MemberDeliveryAddressView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<MemberDeliveryAddressView> listForAdmin(String appId, String memberDeliveryAddressName, String memberDeliveryAddressPhone, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(MemberDeliveryAddressView.APP_ID).is(appId)
                .and(MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME).regex(".*?" + memberDeliveryAddressName + ".*")
                .and(MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE).regex(".*?" + memberDeliveryAddressPhone + ".*")
                .and(MemberDeliveryAddressView.SYSTEM_STATUS).is(true);
        
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, MemberDefaultAvatarView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<MemberDeliveryAddressView> memberDeliveryAddressViewList = list(query, sort, pageIndex, pageSize);

        return memberDeliveryAddressViewList;
    }

}