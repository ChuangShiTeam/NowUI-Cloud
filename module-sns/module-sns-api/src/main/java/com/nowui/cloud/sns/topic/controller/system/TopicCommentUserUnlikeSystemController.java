package com.nowui.cloud.sns.topic.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserUnlike;
import com.nowui.cloud.sns.topic.rpc.TopicCommentUserUnlikeRpc;
import com.nowui.cloud.sns.topic.service.TopicCommentUserUnlikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题评论的取消点赞系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Api(value = "话题评论的取消点赞", description = "话题评论的取消点赞系统端接口管理")
@RestController
public class TopicCommentUserUnlikeSystemController implements TopicCommentUserUnlikeRpc {

}