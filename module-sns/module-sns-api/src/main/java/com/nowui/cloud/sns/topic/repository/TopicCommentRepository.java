package com.nowui.cloud.sns.topic.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.topic.view.TopicCommentView;
import org.springframework.stereotype.Component;

/**
 * 话题评论视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface TopicCommentRepository extends BaseRepository<TopicCommentView> {

}
