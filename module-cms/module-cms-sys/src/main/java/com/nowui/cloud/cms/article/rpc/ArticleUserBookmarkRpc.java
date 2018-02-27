package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章用户收藏服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "articleUserBookmarkRpc")
@FeignClient(name = "module-cms")
public interface ArticleUserBookmarkRpc {

}