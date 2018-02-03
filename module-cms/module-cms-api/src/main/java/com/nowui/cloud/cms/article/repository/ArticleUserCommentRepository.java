package com.nowui.cloud.cms.article.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.article.view.ArticleUserCommentView;
import org.springframework.stereotype.Component;

/**
 * 文章用户评论视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
public interface ArticleUserCommentRepository extends BaseRepository<ArticleUserCommentView> {

}
