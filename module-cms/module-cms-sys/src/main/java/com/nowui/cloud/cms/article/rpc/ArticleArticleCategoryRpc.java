package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleArticleCategoryRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章文章分类服务调用
 *
 * @author marcus
 *
 * 2018-01-03
 */
@Component(value = "ArticleArticleCategoryRpc")
@FeignClient(name = "module-cms", fallback = ArticleArticleCategoryRpcFallback.class)
public interface ArticleArticleCategoryRpc {

}