package com.nowui.cloud.member.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberView;
import org.springframework.stereotype.Component;

/**
 * 会员	视图访问组件接口
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
public interface MemberRepository extends BaseRepository<MemberView> {

}
