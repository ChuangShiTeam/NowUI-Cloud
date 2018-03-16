package com.nowui.cloud.member.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberAvatarView;
import org.springframework.stereotype.Component;

/**
 * 会员头像视图访问组件接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
public interface MemberAvatarRepository extends BaseRepository<MemberAvatarView> {

}
