package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleComment;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleCommentRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章评论服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleCommentRpc")
@FeignClient(name = "module-cms", fallback = ArticleCommentRpcFallback.class)
public interface ArticleCommentRpc {

}