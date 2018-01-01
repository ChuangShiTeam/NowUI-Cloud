package com.nowui.cloud.base.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.member.entity.MemberFollow;
import com.nowui.cloud.base.member.rpc.MemberFollowRpc;
import com.nowui.cloud.base.member.service.MemberFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员关注系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员关注", description = "会员关注系统端接口管理")
@RestController
public class MemberFollowSystemController implements MemberFollowRpc {

}