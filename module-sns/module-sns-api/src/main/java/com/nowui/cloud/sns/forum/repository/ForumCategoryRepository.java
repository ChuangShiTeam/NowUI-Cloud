package com.nowui.cloud.sns.forum.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.forum.view.ForumCategoryView;
import org.springframework.stereotype.Component;

/**
 * 论坛分类视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface ForumCategoryRepository extends BaseRepository<ForumCategoryView> {

}
