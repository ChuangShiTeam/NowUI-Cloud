package com.nowui.cloud.sns.forum.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.rpc.ForumUserFollowRpc;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 论坛用户关注系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户关注", description = "论坛用户关注系统端接口管理")
@RestController
public class ForumUserFollowSystemController implements ForumUserFollowRpc {

}