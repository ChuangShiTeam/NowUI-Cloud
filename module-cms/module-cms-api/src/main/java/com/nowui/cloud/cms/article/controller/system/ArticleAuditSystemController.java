package com.nowui.cloud.cms.article.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.article.entity.ArticleAudit;
import com.nowui.cloud.cms.article.rpc.ArticleAuditRpc;
import com.nowui.cloud.cms.article.service.ArticleAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章审核系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章审核", description = "文章审核系统端接口管理")
@RestController
public class ArticleAuditSystemController implements ArticleAuditRpc {

}