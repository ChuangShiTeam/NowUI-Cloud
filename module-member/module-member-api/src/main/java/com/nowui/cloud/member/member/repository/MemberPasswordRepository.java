package com.nowui.cloud.member.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberPasswordView;
import org.springframework.stereotype.Component;

/**
 * 会员密码视图访问组件接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
public interface MemberPasswordRepository extends BaseRepository<MemberPasswordView> {

}
