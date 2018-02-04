package com.nowui.cloud.sns.topic.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.topic.view.TopicForumView;
import org.springframework.stereotype.Component;

/**
 * 话题论坛视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface TopicForumRepository extends BaseRepository<TopicForumView> {

}
