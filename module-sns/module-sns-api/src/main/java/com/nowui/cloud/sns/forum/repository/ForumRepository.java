package com.nowui.cloud.sns.forum.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.forum.view.ForumView;
import org.springframework.stereotype.Component;

/**
 * 论坛视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface ForumRepository extends BaseRepository<ForumView> {

}
