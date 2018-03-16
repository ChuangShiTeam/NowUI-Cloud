package com.nowui.cloud.sns.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberDialogueView;
import org.springframework.stereotype.Component;

/**
 * 会员对话视图访问组件接口
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
public interface MemberDialogueRepository extends BaseRepository<MemberDialogueView> {

}
