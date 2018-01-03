package com.nowui.cloud.cms.article.rpc;

import com.nowui.cloud.cms.article.entity.ArticleAudit;
import com.nowui.cloud.cms.article.rpc.fallback.ArticleAuditRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章审核服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleAuditRpc")
@FeignClient(name = "module-cms", fallback = ArticleAuditRpcFallback.class)
public interface ArticleAuditRpc {

}