package com.nowui.cloud.sns.forum.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.forum.entity.TopicForum;
import com.nowui.cloud.sns.forum.rpc.TopicForumRpc;
import com.nowui.cloud.sns.forum.service.TopicForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题论坛关联系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题论坛关联", description = "话题论坛关联系统端接口管理")
@RestController
public class TopicForumSystemController implements TopicForumRpc {

}