package com.nowui.cloud.sns.topic.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.topic.view.TopicUserBookmarkView;
import org.springframework.stereotype.Component;

/**
 * 话题收藏视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface TopicUserBookmarkRepository extends BaseRepository<TopicUserBookmarkView> {

}
