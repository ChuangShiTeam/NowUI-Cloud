package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章用户鄙视服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "articleUserDislikeRpc")
@FeignClient(name = "module-cms")
public interface ArticleUserDislikeRpc {

}