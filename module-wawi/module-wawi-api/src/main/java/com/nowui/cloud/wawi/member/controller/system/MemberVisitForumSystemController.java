package com.nowui.cloud.wawi.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.wawi.member.entity.MemberVisitForum;
import com.nowui.cloud.wawi.member.rpc.MemberVisitForumRpc;
import com.nowui.cloud.wawi.member.service.MemberVisitForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员访问圈子系统端控制器
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Api(value = "会员访问圈子", description = "会员访问圈子系统端接口管理")
@RestController
public class MemberVisitForumSystemController implements MemberVisitForumRpc {

}