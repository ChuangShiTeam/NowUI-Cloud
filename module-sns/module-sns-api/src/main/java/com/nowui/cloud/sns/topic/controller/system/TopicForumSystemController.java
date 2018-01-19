package com.nowui.cloud.sns.topic.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.sns.topic.rpc.TopicForumRpc;

import io.swagger.annotations.Api;

/**
 * 话题论坛系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题论坛", description = "话题论坛系统端接口管理")
@RestController
public class TopicForumSystemController implements TopicForumRpc {

}