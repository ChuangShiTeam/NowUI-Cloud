package com.nowui.cloud.base.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.member.entity.MemberInform;
import com.nowui.cloud.base.member.rpc.MemberInformRpc;
import com.nowui.cloud.base.member.service.MemberInformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员举报系统端控制器
 *
 * @author marcus
 *
 * 2018-01-09
 */
@Api(value = "会员举报", description = "会员举报系统端接口管理")
@RestController
public class MemberInformSystemController implements MemberInformRpc {

}