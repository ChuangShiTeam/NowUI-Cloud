package com.nowui.cloud.sns.forum.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import org.springframework.stereotype.Component;

/**
 * 论坛用户关注视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface ForumUserFollowRepository extends BaseRepository<ForumUserFollowView> {

}
