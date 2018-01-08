package com.nowui.cloud.sns.topic.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.rpc.TopicUserUnbookmarkRpc;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题用户取消收藏关联系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户取消收藏关联", description = "话题用户取消收藏关联系统端接口管理")
@RestController
public class TopicUserUnbookmarkSystemController implements TopicUserUnbookmarkRpc {

}