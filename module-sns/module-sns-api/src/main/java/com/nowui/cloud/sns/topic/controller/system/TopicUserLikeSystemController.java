package com.nowui.cloud.sns.topic.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.rpc.TopicUserLikeRpc;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞话题关联系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "点赞话题关联", description = "点赞话题关联系统端接口管理")
@RestController
public class TopicUserLikeSystemController implements TopicUserLikeRpc {

}