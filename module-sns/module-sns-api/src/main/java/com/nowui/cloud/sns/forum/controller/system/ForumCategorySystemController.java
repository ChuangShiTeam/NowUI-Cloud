package com.nowui.cloud.sns.forum.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.sns.forum.entity.ForumCategory;
import com.nowui.cloud.sns.forum.rpc.ForumCategoryRpc;
import com.nowui.cloud.sns.forum.service.ForumCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 论坛分类系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛分类", description = "论坛分类系统端接口管理")
@RestController
public class ForumCategorySystemController implements ForumCategoryRpc {

}