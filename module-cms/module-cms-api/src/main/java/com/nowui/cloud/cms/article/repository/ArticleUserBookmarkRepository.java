package com.nowui.cloud.cms.article.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.article.view.ArticleUserBookmarkView;
import org.springframework.stereotype.Component;

/**
 * 文章用户收藏视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
public interface ArticleUserBookmarkRepository extends BaseRepository<ArticleUserBookmarkView> {

}
