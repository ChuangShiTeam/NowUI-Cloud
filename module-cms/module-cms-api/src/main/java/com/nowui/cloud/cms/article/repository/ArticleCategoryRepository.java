package com.nowui.cloud.cms.article.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import org.springframework.stereotype.Component;

/**
 * 文章分类视图访问组件接口
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
@Component
public interface ArticleCategoryRepository extends BaseRepository<ArticleCategoryView> {

}
