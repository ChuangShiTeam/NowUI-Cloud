package com.nowui.cloud.cms.article.rpc.fallback;

import com.nowui.cloud.cms.article.entity.ArticleDislike;
import com.nowui.cloud.cms.article.rpc.ArticleDislikeRpc;
import org.springframework.stereotype.Component;

/**
 * 文章鄙视服务调用异常处理
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleDislikeRpcFallback")
public class ArticleDislikeRpcFallback implements ArticleDislikeRpc {

}
