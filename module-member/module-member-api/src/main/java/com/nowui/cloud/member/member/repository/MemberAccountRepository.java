package com.nowui.cloud.member.member.repository;

import org.springframework.stereotype.Component;

import com.nowui.cloud.member.member.view.MemberAccountView;
import com.nowui.cloud.repository.BaseRepository;

/**
 * 会员账号视图访问组件接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
public interface MemberAccountRepository extends BaseRepository<MemberAccountView> {

}
