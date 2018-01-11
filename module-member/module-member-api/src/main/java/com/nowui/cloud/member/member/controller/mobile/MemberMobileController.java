package com.nowui.cloud.member.member.controller.mobile;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.entity.enums.SmsCaptchaType;
import com.nowui.cloud.base.sms.rpc.SmsCaptchaRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.util.AesUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员移动端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员", description = "会员移动端接口管理")
@RestController
public class MemberMobileController extends BaseController {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private SmsCaptchaRpc smsCaptchaRpc;
    
    @Autowired
    private UserRpc userRpc;
    
    @ApiOperation(value = "会员手机登录验证码发送")
    @RequestMapping(value = "/member/mobile/v1/login/sms/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> loginSmsCaptchaSendV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                BaseEntity.SYSTEM_REQUEST_IP_ADDRESS
        );

        smsCaptchaRpc.aliyunSend(body.getAppId(), SmsCaptchaType.LOGIN.getKey(), body.getUserAccount(), body.getSystemRequestIpAddress(), 1, body.getSystemRequestUserId());
        
        return renderJson(true);
    }
    
    @ApiOperation(value = "会员手机注册验证码发送")
    @RequestMapping(value = "/member/mobile/v1/register/sms/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> registerSmsCaptchaSendV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                BaseEntity.SYSTEM_REQUEST_IP_ADDRESS
        );

        smsCaptchaRpc.aliyunSend(body.getAppId(), SmsCaptchaType.REGISTER.getKey(), body.getUserAccount(), body.getSystemRequestIpAddress(), 1, body.getSystemRequestUserId());
        
        return renderJson(true);
    }
    
    @ApiOperation(value = "会员忘记密码手机验证码发送")
    @RequestMapping(value = "/member/mobile/v1/forget/password/sms/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> forgetPasswordSmsCaptchaSendV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                BaseEntity.SYSTEM_REQUEST_IP_ADDRESS
        );

        smsCaptchaRpc.aliyunSend(body.getAppId(), SmsCaptchaType.FORGET_PASSWORD.getKey(), body.getUserAccount(), body.getSystemRequestIpAddress(), 1, body.getSystemRequestUserId());
        
        return renderJson(true);
    }
    
    @ApiOperation(value = "会员手机验证码登录")
    @RequestMapping(value = "/member/mobile/v1/sms/captcha/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> captchaLoginV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        SmsCaptcha smsCaptcha = JSONObject.parseObject(body.toJSONString(), SmsCaptcha.class);
        validateRequest(
                smsCaptcha,
                SmsCaptcha.SMS_CAPTCHA_CODE
        );
        //验证手机号码是否已经注册
        User user = userRpc.findByUserAccount(body.getAppId(), body.getUserAccount());
        if (user == null) {
            throw new RuntimeException("用户未注册");
        }
        //验证验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCode(body.getAppId(), body.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), calendar1.getTime());
        if (!isCorrect) {
            throw new RuntimeException("验证码错误");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUserId());
            jsonObject.put(Constant.EXPIRE_TIME, calendar2.getTime());
            
            result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Constant.PRIVATE_KEY));
            validateResponse(Constant.TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员密码登录")
    @RequestMapping(value = "/member/mobile/v1/password/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> passwordLoginV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        SmsCaptcha smsCaptcha = JSONObject.parseObject(body.toJSONString(), SmsCaptcha.class);
        validateRequest(
                smsCaptcha,
                SmsCaptcha.SMS_CAPTCHA_CODE
        );
        //验证手机号码是否已经注册
        User user = userRpc.findByUserAccount(body.getAppId(), body.getUserAccount());
        if (user == null) {
            throw new RuntimeException("用户未注册");
        }
        //验证验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCode(body.getAppId(), body.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), calendar1.getTime());
        if (!isCorrect) {
            throw new RuntimeException("验证码错误");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUserId());
            jsonObject.put(Constant.EXPIRE_TIME, calendar2.getTime());
            
            result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Constant.PRIVATE_KEY));
            validateResponse(Constant.TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
        
        return renderJson(result);
    }

}