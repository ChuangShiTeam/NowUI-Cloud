package com.nowui.cloud.cms.article.rpc.fallback;

import com.nowui.cloud.cms.article.entity.ArticleAudit;
import com.nowui.cloud.cms.article.rpc.ArticleAuditRpc;
import org.springframework.stereotype.Component;

/**
 * 文章审核服务调用异常处理
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleAuditRpcFallback")
public class ArticleAuditRpcFallback implements ArticleAuditRpc {

}
