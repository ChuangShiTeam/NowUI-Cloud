package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleCommentLike;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleCommentLikeRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章评论点赞服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleCommentLikeRpc")
@FeignClient(name = "module-cms", fallback = ArticleCommentLikeRpcFallback.class)
public interface ArticleCommentLikeRpc {

}