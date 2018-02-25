package com.nowui.cloud.member.member.controller.mobile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.entity.enums.SmsCaptchaType;
import com.nowui.cloud.base.sms.rpc.SmsCaptchaRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.util.AesUtil;
import com.nowui.cloud.util.DateUtil;
import com.nowui.cloud.util.Util;

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
    
    @Autowired
    private FileRpc fileRpc;
    
    @ApiOperation(value = "会员手机号码注册")
    @RequestMapping(value = "/member/mobile/v1/mobile/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> mobileRegisterV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        UserPassword userPassword = getEntry(UserPassword.class);
        validateRequest(
                userPassword,
                UserPassword.USER_PASSWORD
        );
        SmsCaptcha smsCaptcha = getEntry(SmsCaptcha.class);
        validateRequest(
                smsCaptcha,
                SmsCaptcha.SMS_CAPTCHA_CODE
        );
        if (!Util.isPhone(body.getUserAccount())) {
            throw new BusinessException("手机号码格式不对");
        }
        
        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        
        if (user != null) {
            throw new BusinessException("用户已注册");
        }
        
        // 验证验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCode(body.getAppId(), body.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), DateUtil.getDateTimeString(calendar1.getTime()));
        if (!isCorrect) {
            throw new BusinessException("验证码错误");
        }
        
        Member member = new Member();
        
        String userId = Util.getRandomUUID();
        String memberId = Util.getRandomUUID();
        
        member.setAppId(body.getAppId());
        member.setMemberId(memberId);
        member.setUserId(userId);
        member.setMemberIsRecommed(false);
        member.setMemberIsTop(false);
        member.setMemberTopEndTime(new Date());
        member.setMemberTopLevel(0);

        Member result = memberService.save(member,memberId ,body.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            userRpc.registerUserMobileV1(body.getAppId(), userId, memberId, UserType.MEMBER.getKey(), body.getUserAccount(), userPassword.getUserPassword(), body.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "会员邮箱注册")
    @RequestMapping(value = "/member/mobile/v1/email/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> emailRegisterV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        UserPassword userPassword = getEntry(UserPassword.class);
        validateRequest(
                userPassword,
                UserPassword.USER_PASSWORD
        );
        
        if (!Util.isEmail(body.getUserAccount())) {
            throw new BusinessException("邮箱格式不对");
        }
        
        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        
        if (user != null) {
            throw new BusinessException("用户已注册");
        }
        
        Member member = new Member();
        
        String userId = Util.getRandomUUID();
        String memberId = Util.getRandomUUID();
        
        member.setAppId(body.getAppId());
        member.setMemberId(memberId);
        member.setUserId(userId);
        member.setMemberIsRecommed(false);
        member.setMemberIsTop(false);
        member.setMemberTopEndTime(new Date());
        member.setMemberTopLevel(0);

        Member result = memberService.save(member,memberId,member.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            userRpc.registerUserEmailV1(body.getAppId(), userId, memberId, UserType.MEMBER.getKey(), body.getUserAccount(), userPassword.getUserPassword(), body.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "会员手机登录验证码发送")
    @RequestMapping(value = "/member/mobile/v1/login/sms/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> loginSmsCaptchaSendV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                BaseEntity.SYSTEM_REQUEST_IP_ADDRESS
        );
        
        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        
        if (user == null) {
            throw new BusinessException("用户未注册");
        }

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
        
        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        
        if (user != null) {
            throw new BusinessException("手机号码已注册");
        }

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

        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        
        if (user == null) {
            throw new BusinessException("用户未注册");
        }
        
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
        SmsCaptcha smsCaptcha = getEntry(SmsCaptcha.class);
        validateRequest(
                smsCaptcha,
                SmsCaptcha.SMS_CAPTCHA_CODE
        );
        //验证手机号码是否已经注册
        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        if (user == null) {
            throw new BusinessException("用户未注册");
        }
        //验证验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCode(body.getAppId(), body.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), DateUtil.getDateTimeString(calendar1.getTime()));
        if (!isCorrect) {
            throw new BusinessException("验证码错误");
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
            throw new BusinessException("登录不成功");
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
        UserPassword userPassword = getEntry(UserPassword.class);
        validateRequest(
                userPassword,
                UserPassword.USER_PASSWORD
        );
        //验证手机号码是否已经注册
        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        //验证密码是否正确
        Boolean isCorrect = userRpc.checkUserPasswordV1(user.getUserId(), userPassword.getUserPassword());
        
        if (!isCorrect) {
            throw new BusinessException("用户名或密码错误");
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
            throw new BusinessException("登录不成功");
        }
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员忘记密码验证")
    @RequestMapping(value = "/member/mobile/v1/forget/password/check", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> forgetPasswordCheckV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        SmsCaptcha smsCaptcha = getEntry(SmsCaptcha.class);
        validateRequest(
                smsCaptcha,
                SmsCaptcha.SMS_CAPTCHA_CODE
        );
        //验证手机号码是否已经注册
        User user = userRpc.findByUserAccountV1(body.getAppId(), UserType.MEMBER.getKey(), body.getUserAccount());
        if (user == null) {
            throw new BusinessException("用户未注册");
        }
        //验证手机验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCode(body.getAppId(), body.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), DateUtil.getDateTimeString(calendar1.getTime()));
        if (!isCorrect) {
            throw new BusinessException("验证码错误");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userId", user.getUserId());
        
        validateResponse("userId");
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员忘记密码找回")
    @RequestMapping(value = "/member/mobile/v1/forget/password/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> forgetPasswordFindV1(@RequestBody UserPassword body) {
        validateRequest(
                body,
                UserPassword.APP_ID,
                UserPassword.USER_ID,
                UserPassword.USER_PASSWORD
        );
        
        Boolean result = userRpc.updateUserPasswordV1(body.getAppId(), body.getUserId(), body.getUserPassword(), body.getSystemRequestUserId());
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员密码更新")
    @RequestMapping(value = "/member/mobile/v1/update/password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updatePasswordV1(@RequestBody UserPassword body) {
        validateRequest(
                body,
                UserPassword.APP_ID,
                UserPassword.SYSTEM_REQUEST_USER_ID,
                UserPassword.USER_PASSWORD
        );
        
        Boolean result = userRpc.updateUserPasswordV1(body.getAppId(), body.getSystemRequestUserId(), body.getUserPassword(), body.getSystemRequestUserId());
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员昵称更新")
    @RequestMapping(value = "/member/mobile/v1/update/nick/name", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateNickNameV1(@RequestBody UserNickName body) {
        validateRequest(
                body,
                UserNickName.APP_ID,
                UserNickName.SYSTEM_REQUEST_USER_ID,
                UserNickName.USER_NICK_NAME
        );
        
        Boolean result = userRpc.updateUserNickNameV1(body.getAppId(), body.getSystemRequestUserId(), body.getUserNickName(), body.getSystemRequestUserId());
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员头像更新")
    @RequestMapping(value = "/member/mobile/v1/update/avatar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateAvatarV1(@RequestBody UserAvatar body) {
        validateRequest(
                body,
                UserAvatar.APP_ID,
                UserAvatar.SYSTEM_REQUEST_USER_ID,
                UserAvatar.USER_AVATAR
        );
        
        Boolean result = userRpc.updateUserAvatarV1(body.getAppId(), body.getSystemRequestUserId(), body.getUserAvatar(), body.getSystemRequestUserId());
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员签名更新")
    @RequestMapping(value = "/member/mobile/v1/update/signature", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateSignatureV1(@RequestBody UserAvatar body) {
        validateRequest(
                body,
                UserAvatar.APP_ID,
                UserAvatar.SYSTEM_REQUEST_USER_ID,
                Member.MEMBER_SIGNATURE
        );
        
        Boolean result = userRpc.updateUserAvatarV1(body.getAppId(), body.getSystemRequestUserId(), body.getUserAvatar(), body.getSystemRequestUserId());
        
        return renderJson(result);
    }
    
}