package com.nowui.cloud.base.member.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.member.entity.Member;
import com.nowui.cloud.base.member.service.MemberService;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员移动端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员", description = "会员移动端接口管理")
@RestController
public class MemberMobileController extends BaseController {
    
    @Autowired
    private MemberService memberService;
    
    @ApiOperation(value = "注册会员")
    @RequestMapping(value = "/member/mobile/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> register(@RequestBody Member body) {
        validateRequest(
                body,
                Member.APP_ID,
                Member.USER_ID
        );

        Boolean result = memberService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }
    
    @ApiOperation(value = "注册会员发送验证码")
    @RequestMapping(value = "/member/mobile/register/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody User body) {
        validateRequest(
                body,
                body.APP_ID,
                body.USER_MOBILE
        );

        

        return renderJson(true);
    }

}