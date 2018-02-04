package com.nowui.cloud.sns.topic.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.topic.view.TopicTipView;
import org.springframework.stereotype.Component;

/**
 * 话题提醒视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface TopicTipRepository extends BaseRepository<TopicTipView> {

}
