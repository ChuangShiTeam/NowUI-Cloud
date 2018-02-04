package com.nowui.cloud.sns.topic.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.topic.view.TopicView;
import org.springframework.stereotype.Component;

/**
 * 话题视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface TopicRepository extends BaseRepository<TopicView> {

}
