package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleLike;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleLikeRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章点赞服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleLikeRpc")
@FeignClient(name = "module-cms", fallback = ArticleLikeRpcFallback.class)
public interface ArticleLikeRpc {

}