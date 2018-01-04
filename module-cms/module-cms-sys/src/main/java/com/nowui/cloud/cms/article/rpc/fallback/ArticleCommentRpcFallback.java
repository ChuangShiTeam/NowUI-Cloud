package com.nowui.cloud.cms.article.rpc.fallback;

import com.nowui.cloud.cms.article.entity.ArticleComment;
import com.nowui.cloud.cms.article.rpc.ArticleCommentRpc;
import org.springframework.stereotype.Component;

/**
 * 文章评论服务调用异常处理
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleCommentRpcFallback")
public class ArticleCommentRpcFallback implements ArticleCommentRpc {

}
