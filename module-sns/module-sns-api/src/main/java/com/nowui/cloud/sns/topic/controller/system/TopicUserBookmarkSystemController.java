package com.nowui.cloud.sns.topic.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.rpc.TopicUserBookmarkRpc;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题收藏系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题收藏", description = "话题收藏系统端接口管理")
@RestController
public class TopicUserBookmarkSystemController implements TopicUserBookmarkRpc {

}