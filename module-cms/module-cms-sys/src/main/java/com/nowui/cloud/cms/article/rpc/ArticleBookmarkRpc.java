package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleBookmark;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleBookmarkRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章收藏服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleBookmarkRpc")
@FeignClient(name = "module-cms", fallback = ArticleBookmarkRpcFallback.class)
public interface ArticleBookmarkRpc {

}