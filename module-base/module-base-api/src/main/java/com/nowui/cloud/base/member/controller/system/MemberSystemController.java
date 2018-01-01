package com.nowui.cloud.base.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.member.entity.Member;
import com.nowui.cloud.base.member.rpc.MemberRpc;
import com.nowui.cloud.base.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员", description = "会员系统端接口管理")
@RestController
public class MemberSystemController implements MemberRpc {

}