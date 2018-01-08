package com.nowui.cloud.base.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.member.entity.MemberAddress;
import com.nowui.cloud.base.member.rpc.MemberAddressRpc;
import com.nowui.cloud.base.member.service.MemberAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员地址系统端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员地址", description = "会员地址系统端接口管理")
@RestController
public class MemberAddressSystemController implements MemberAddressRpc {

}