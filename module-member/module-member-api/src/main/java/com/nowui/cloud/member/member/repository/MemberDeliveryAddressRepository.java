package com.nowui.cloud.member.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberDeliveryAddressView;
import org.springframework.stereotype.Component;

/**
 * 用户收货地址	视图访问组件接口
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
public interface MemberDeliveryAddressRepository extends BaseRepository<MemberDeliveryAddressView> {

}
