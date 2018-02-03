package com.nowui.cloud.cms.article.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.article.view.ArticleArticleCategoryView;
import org.springframework.stereotype.Component;

/**
 * 文章分类关联视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
public interface ArticleArticleCategoryRepository extends BaseRepository<ArticleArticleCategoryView> {

}
