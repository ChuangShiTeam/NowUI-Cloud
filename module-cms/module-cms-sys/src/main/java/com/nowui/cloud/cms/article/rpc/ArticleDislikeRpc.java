package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleDislike;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleDislikeRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章鄙视服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleDislikeRpc")
@FeignClient(name = "module-cms", fallback = ArticleDislikeRpcFallback.class)
public interface ArticleDislikeRpc {

}