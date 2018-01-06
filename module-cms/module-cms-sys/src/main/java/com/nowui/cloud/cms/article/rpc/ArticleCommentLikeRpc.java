package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章评论点赞服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleCommentLikeRpc")
@FeignClient(name = "module-cms")
public interface ArticleCommentLikeRpc {

}