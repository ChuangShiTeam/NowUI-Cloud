package com.nowui.cloud.cms.article.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.article.view.ArticleUserCommentUserLikeView;
import org.springframework.stereotype.Component;

/**
 * 文章用户评论点赞视图访问组件接口
 *
 * @author x
 *
 * 2018-02-03
 */
@Component
public interface ArticleUserCommentUserLikeRepository extends BaseRepository<ArticleUserCommentUserLikeView> {

}
