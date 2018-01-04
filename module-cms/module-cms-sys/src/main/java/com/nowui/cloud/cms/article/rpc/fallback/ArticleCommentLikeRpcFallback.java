package com.nowui.cloud.cms.article.rpc.fallback;

import com.nowui.cloud.cms.article.entity.ArticleCommentLike;
import com.nowui.cloud.cms.article.rpc.ArticleCommentLikeRpc;
import org.springframework.stereotype.Component;

/**
 * 文章评论点赞服务调用异常处理
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleCommentLikeRpcFallback")
public class ArticleCommentLikeRpcFallback implements ArticleCommentLikeRpc {

}
