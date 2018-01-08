package com.nowui.cloud.sns.forum.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 论坛分类服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "forumCategoryRpc")
@FeignClient(name = "module-sns")
public interface ForumCategoryRpc {

}