package com.nowui.cloud.sns.forum.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;
import org.springframework.stereotype.Component;

/**
 * 论坛取消关注视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface ForumUserUnfollowRepository extends BaseRepository<ForumUserUnfollowView> {

}
