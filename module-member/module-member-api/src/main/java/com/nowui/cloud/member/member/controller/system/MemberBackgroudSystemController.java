package com.nowui.cloud.member.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.member.member.entity.MemberBackgroud;
import com.nowui.cloud.member.member.rpc.MemberBackgroudRpc;
import com.nowui.cloud.member.member.service.MemberBackgroudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员背景系统端控制器
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Api(value = "会员背景", description = "会员背景系统端接口管理")
@RestController
public class MemberBackgroudSystemController implements MemberBackgroudRpc {

}