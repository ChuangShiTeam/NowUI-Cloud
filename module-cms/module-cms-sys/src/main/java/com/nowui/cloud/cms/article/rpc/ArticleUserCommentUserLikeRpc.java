package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章用户评论用户点赞服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "articleUserCommentUserLikeRpc")
@FeignClient(name = "module-cms")
public interface ArticleUserCommentUserLikeRpc {

}