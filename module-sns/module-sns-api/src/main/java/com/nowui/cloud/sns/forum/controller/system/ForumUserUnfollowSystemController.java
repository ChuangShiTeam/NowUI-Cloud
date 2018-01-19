package com.nowui.cloud.sns.forum.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.sns.forum.rpc.ForumUserUnfollowRpc;

import io.swagger.annotations.Api;

/**
 * 论坛用户取关系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户取关", description = "论坛用户取关系统端接口管理")
@RestController
public class ForumUserUnfollowSystemController implements ForumUserUnfollowRpc {

}