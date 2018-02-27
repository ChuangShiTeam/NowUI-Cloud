package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章审核服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleAuditRpc")
@FeignClient(name = "module-cms")
public interface ArticleAuditRpc {

}