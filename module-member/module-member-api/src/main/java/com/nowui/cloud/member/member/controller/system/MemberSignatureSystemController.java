package com.nowui.cloud.member.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.rpc.MemberSignatureRpc;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 	会员签名系统端控制器
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Api(value = "	会员签名", description = "	会员签名系统端接口管理")
@RestController
public class MemberSignatureSystemController implements MemberSignatureRpc {

}