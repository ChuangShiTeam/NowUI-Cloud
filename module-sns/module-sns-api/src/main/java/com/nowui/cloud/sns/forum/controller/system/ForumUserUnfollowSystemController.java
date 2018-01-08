package com.nowui.cloud.sns.forum.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.rpc.ForumUserUnfollowRpc;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 论坛用户取关关联系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户取关关联", description = "论坛用户取关关联系统端接口管理")
@RestController
public class ForumUserUnfollowSystemController implements ForumUserUnfollowRpc {

}