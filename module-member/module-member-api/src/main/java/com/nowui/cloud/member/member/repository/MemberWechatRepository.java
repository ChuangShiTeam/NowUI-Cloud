package com.nowui.cloud.member.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberWechatView;
import org.springframework.stereotype.Component;

/**
 * 会员微信视图访问组件接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
public interface MemberWechatRepository extends BaseRepository<MemberWechatView> {

}
