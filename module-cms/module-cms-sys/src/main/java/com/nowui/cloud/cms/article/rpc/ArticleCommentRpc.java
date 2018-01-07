package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章评论服务调用
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component(value = "ArticleCommentRpc")
@FeignClient(name = "module-cms")
public interface ArticleCommentRpc {

}