package com.nowui.cloud.sns.forum.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 论坛背景服务调用
 *
 * @author xupengfei
 *
 * 2018-03-09
 */
@Component(value = "forumBackgroundMediaRpc")
@FeignClient(name = "module-sns")
public interface ForumBackgroundMediaRpc {

}