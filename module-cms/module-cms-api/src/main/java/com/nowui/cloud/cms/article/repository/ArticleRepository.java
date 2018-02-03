package com.nowui.cloud.cms.article.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.article.view.ArticleView;
import org.springframework.stereotype.Component;

/**
 * 文章视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
public interface ArticleRepository extends BaseRepository<ArticleView> {

}
