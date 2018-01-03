package com.nowui.cloud.cms.article.rpc.fallback;

import com.nowui.cloud.cms.article.entity.ArticleLike;
import com.nowui.cloud.cms.article.rpc.ArticleLikeRpc;
import org.springframework.stereotype.Component;

/**
 * 文章点赞服务调用异常处理
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleLikeRpcFallback")
public class ArticleLikeRpcFallback implements ArticleLikeRpc {

}
