package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章服务调用
 * 
 * @author marcus
 *
 * 2018年1月15日
 */
@Component(value = "articleRpc")
@FeignClient(name = "module-cms")
public interface ArticleRpc {

}
