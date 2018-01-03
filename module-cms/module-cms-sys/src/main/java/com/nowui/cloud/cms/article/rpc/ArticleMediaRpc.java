package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleMediaRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章多媒体服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleMediaRpc")
@FeignClient(name = "module-cms", fallback = ArticleMediaRpcFallback.class)
public interface ArticleMediaRpc {

}