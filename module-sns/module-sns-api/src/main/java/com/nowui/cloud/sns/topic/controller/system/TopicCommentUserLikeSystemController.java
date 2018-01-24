package com.nowui.cloud.sns.topic.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.rpc.TopicCommentUserLikeRpc;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题的评论用户点赞系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Api(value = "话题的评论用户点赞", description = "话题的评论用户点赞系统端接口管理")
@RestController
public class TopicCommentUserLikeSystemController implements TopicCommentUserLikeRpc {

}