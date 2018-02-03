package com.nowui.cloud.cms.article.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.article.view.ArticleUserDislikeView;
import org.springframework.stereotype.Component;

/**
 * 文章用户鄙视视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
public interface ArticleUserDislikeRepository extends BaseRepository<ArticleUserDislikeView> {

}
