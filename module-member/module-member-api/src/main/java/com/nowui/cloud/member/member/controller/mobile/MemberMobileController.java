package com.nowui.cloud.member.member.controller.mobile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.entity.enums.SmsCaptchaType;
import com.nowui.cloud.base.sms.rpc.SmsCaptchaRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberPreferenceLanguage;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.view.MemberView;
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
    
    @ApiOperation(value = "会员手机号码注册")
    @RequestMapping(value = "/member/mobile/v1/mobile/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> mobileRegisterV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                UserAccount.SYSTEM_REQUEST_USER_ID
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
        
        if (!Util.isPhone(userAccount.getUserAccount())) {
            throw new BusinessException("手机号码格式不对");
        }
        
        Boolean isRegister = userRpc.checkUserAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        
        if (isRegister) {
            throw new BusinessException("用户已注册");
        }
        
        // 验证验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCodeV1(userAccount.getAppId(), userAccount.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), DateUtil.getDateTimeString(calendar1.getTime()));
        if (!isCorrect) {
            throw new BusinessException("验证码错误");
        }
        
        Member member = new Member();
        
        String userId = Util.getRandomUUID();
        String memberId = Util.getRandomUUID();
        
        member.setAppId(userAccount.getAppId());
        member.setMemberId(memberId);
        member.setUserId(userId);
        member.setMemberIsRecommed(false);
        member.setMemberIsTop(false);
        member.setMemberTopEndTime(new Date());
        member.setMemberTopLevel(0);

        Member result = memberService.save(member, memberId, userAccount.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            userRpc.registerUserMobileV1(userAccount.getAppId(), userId, memberId, UserType.MEMBER.getKey(), userAccount.getUserAccount(), userPassword.getUserPassword(), userAccount.getSystemRequestUserId());

            // 保存会员视图到MongoDB
            MemberView memberView = JSON.parseObject(result.toJSONString(), MemberView.class);
            
            memberService.save(memberView);
            
            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "会员邮箱注册")
    @RequestMapping(value = "/member/mobile/v1/email/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> emailRegisterV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                UserAccount.SYSTEM_REQUEST_USER_ID
        );
        
        UserPassword userPassword = getEntry(UserPassword.class);
        validateRequest(
                userPassword,
                UserPassword.USER_PASSWORD
        );
        
        if (!Util.isEmail(userAccount.getUserAccount())) {
            throw new BusinessException("邮箱格式不对");
        }
        
        Boolean isRegister = userRpc.checkUserAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        
        if (isRegister) {
            throw new BusinessException("用户已注册");
        }
        
        Member member = new Member();
        
        String userId = Util.getRandomUUID();
        String memberId = Util.getRandomUUID();
        
        member.setAppId(userAccount.getAppId());
        member.setMemberId(memberId);
        member.setUserId(userId);
        member.setMemberIsRecommed(false);
        member.setMemberIsTop(false);
        member.setMemberTopEndTime(new Date());
        member.setMemberTopLevel(0);

        Member result = memberService.save(member,memberId,member.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            userRpc.registerUserEmailV1(userAccount.getAppId(), userId, memberId, UserType.MEMBER.getKey(), userAccount.getUserAccount(), userPassword.getUserPassword(), userAccount.getSystemRequestUserId());

            // 保存会员视图到MongoDB
            MemberView memberView = JSON.parseObject(result.toJSONString(), MemberView.class);
            
            memberService.save(memberView);
            
            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "会员手机登录验证码发送")
    @RequestMapping(value = "/member/mobile/v1/login/sms/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> loginSmsCaptchaSendV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                BaseEntity.SYSTEM_REQUEST_IP_ADDRESS
        );
        
        Boolean isRegister = userRpc.checkUserAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        
        if (!isRegister) {
            throw new BusinessException("用户未注册");
        }

        smsCaptchaRpc.aliyunSendV1(userAccount.getAppId(), SmsCaptchaType.LOGIN.getKey(), userAccount.getUserAccount(), userAccount.getSystemRequestIpAddress(), 1, userAccount.getSystemRequestUserId());
        
        return renderJson(true);
    }
    
    @ApiOperation(value = "会员手机注册验证码发送")
    @RequestMapping(value = "/member/mobile/v1/register/sms/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> registerSmsCaptchaSendV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                BaseEntity.SYSTEM_REQUEST_IP_ADDRESS
        );
        
        Boolean isRegister = userRpc.checkUserAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        
        if (isRegister) {
            throw new BusinessException("手机号码已注册");
        }

        smsCaptchaRpc.aliyunSendV1(userAccount.getAppId(), SmsCaptchaType.REGISTER.getKey(), userAccount.getUserAccount(), userAccount.getSystemRequestIpAddress(), 1, userAccount.getSystemRequestUserId());
        
        return renderJson(true);
    }
    
    @ApiOperation(value = "会员忘记密码手机验证码发送")
    @RequestMapping(value = "/member/mobile/v1/forget/password/sms/captcha/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> forgetPasswordSmsCaptchaSendV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT,
                BaseEntity.SYSTEM_REQUEST_IP_ADDRESS
        );

        Boolean isRegister = userRpc.checkUserAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        
        if (!isRegister) {
            throw new BusinessException("用户未注册");
        }
        
        smsCaptchaRpc.aliyunSendV1(userAccount.getAppId(), SmsCaptchaType.FORGET_PASSWORD.getKey(), userAccount.getUserAccount(), userAccount.getSystemRequestIpAddress(), 1, userAccount.getSystemRequestUserId());
        
        return renderJson(true);
    }
    
    @ApiOperation(value = "会员手机验证码登录")
    @RequestMapping(value = "/member/mobile/v1/sms/captcha/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> captchaLoginV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        SmsCaptcha smsCaptcha = getEntry(SmsCaptcha.class);
        validateRequest(
                smsCaptcha,
                SmsCaptcha.SMS_CAPTCHA_CODE
        );
        //验证手机号码是否已经注册
        UserView userView = userRpc.findByUserTypeAndAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        
        if (userView == null) {
            throw new BusinessException("用户未注册");
        }
        //验证验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCodeV1(userAccount.getAppId(), userAccount.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), DateUtil.getDateTimeString(calendar1.getTime()));
        if (!isCorrect) {
            throw new BusinessException("验证码错误");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, userView.getUserId());
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
    public Map<String, Object> passwordLoginV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        
        UserPassword userPassword = getEntry(UserPassword.class);
        validateRequest(
                userPassword,
                UserPassword.USER_PASSWORD
        );
        //验证手机号码是否已经注册
        UserView userView = userRpc.findByUserTypeAndAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        
        if (userView == null) {
            throw new BusinessException("用户未注册");
        }
        //验证密码是否正确
        Boolean isCorrect = userRpc.checkUserPasswordV1(userView.getUserId(), userPassword.getUserPassword());
        
        if (!isCorrect) {
            throw new BusinessException("用户名或密码错误");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, userView.getUserId());
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
    public Map<String, Object> forgetPasswordCheckV1() {
        UserAccount userAccount = getEntry(UserAccount.class);
        validateRequest(
                userAccount,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT
        );
        
        SmsCaptcha smsCaptcha = getEntry(SmsCaptcha.class);
        validateRequest(
                smsCaptcha,
                SmsCaptcha.SMS_CAPTCHA_CODE
        );
        //验证手机号码是否已经注册
        UserView userView = userRpc.findByUserTypeAndAccountV1(userAccount.getAppId(), UserType.MEMBER.getKey(), userAccount.getUserAccount());
        if (userView == null) {
            throw new BusinessException("用户未注册");
        }
        //验证手机验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCodeV1(userAccount.getAppId(), userAccount.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), DateUtil.getDateTimeString(calendar1.getTime()));
        if (!isCorrect) {
            throw new BusinessException("验证码错误");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(UserView.USER_ID, userView.getUserId());
        
        validateResponse(UserView.USER_ID);
        
        return renderJson(result);
    }
    
    @ApiOperation(value = "会员签名更新")
    @RequestMapping(value = "/member/mobile/v1/update/signature", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateSignatureV1() {
        MemberSignature memberSignature = getEntry(MemberSignature.class);
        validateRequest(
                memberSignature,
                MemberSignature.APP_ID,
                MemberSignature.MEMBER_ID,
                MemberSignature.SYSTEM_REQUEST_USER_ID,
                MemberSignature.MEMBER_SIGNATURE
        );
        
        Boolean result = memberService.updateMemberSignature(memberSignature.getAppId(), memberSignature.getMemberId(), memberSignature.getMemberSignature(), memberSignature.getSystemRequestUserId());

        return renderJson(result);
    }
    
    @ApiOperation(value = "会员背景更新")
    @RequestMapping(value = "/member/mobile/v1/update/background", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateBackgroundV1() {
        MemberBackground memberBackground = getEntry(MemberBackground.class);
        validateRequest(
                memberBackground,
                MemberBackground.APP_ID,
                MemberBackground.MEMBER_ID,
                MemberBackground.SYSTEM_REQUEST_USER_ID,
                MemberBackground.MEMBER_BACKGROUND
        );
        
        File file = getEntry(File.class);
        validateRequest(
                file,
                File.FILE_PATH
        );
        
        Boolean result = memberService.updateMemberBackground(memberBackground.getAppId(), memberBackground.getMemberId(), memberBackground.getMemberBackground(), file.getFilePath(), memberBackground.getSystemRequestUserId());

        return renderJson(result);
    }
    
    @ApiOperation(value = "会员偏好语言更新")
    @RequestMapping(value = "/member/mobile/v1/update/preference/language", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updatePreferenceLanguageV1() {
        MemberPreferenceLanguage memberPreferenceLanguage = getEntry(MemberPreferenceLanguage.class);
        validateRequest(
                memberPreferenceLanguage,
                MemberPreferenceLanguage.APP_ID,
                MemberPreferenceLanguage.MEMBER_ID,
                MemberPreferenceLanguage.SYSTEM_REQUEST_USER_ID,
                MemberPreferenceLanguage.MEMBER_PREFERENCE_LANGUAGE
        );
        
        Boolean result = memberService.updateMemberPreferenceLanguage(memberPreferenceLanguage.getAppId(), memberPreferenceLanguage.getMemberId(), memberPreferenceLanguage.getMemberPreferenceLanguage(), memberPreferenceLanguage.getSystemRequestUserId());

        return renderJson(result);
    }
    
}