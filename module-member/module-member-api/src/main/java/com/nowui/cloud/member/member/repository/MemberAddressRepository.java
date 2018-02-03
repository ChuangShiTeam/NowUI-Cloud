package com.nowui.cloud.member.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberAddressView;
import org.springframework.stereotype.Component;

/**
 * 会员地址	视图访问组件接口
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
public interface MemberAddressRepository extends BaseRepository<MemberAddressView> {

}
