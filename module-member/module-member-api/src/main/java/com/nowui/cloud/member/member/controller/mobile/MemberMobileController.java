package com.nowui.cloud.member.member.controller.mobile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;
import com.nowui.cloud.base.app.rpc.AppConfigRpc;
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.entity.enums.SmsCaptchaType;
import com.nowui.cloud.base.sms.rpc.SmsCaptchaRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.router.UserRouter;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberPreferenceLanguage;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.router.MemberRouter;
import com.nowui.cloud.member.member.router.MemberSignatureRouter;
import com.nowui.cloud.member.member.service.MemberDefaultAvatarService;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;
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
    private AppConfigRpc appConfigRpc;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MemberDefaultAvatarService memberDefaultAvatarService;
    
    @Autowired
    private SmsCaptchaRpc smsCaptchaRpc;
    
    @Autowired
    private UserRpc userRpc;
    
    @ApiOperation(value = "会员微信授权登录")
    @GetMapping(value = "/member/mobile/v1/wechat/auth/login")
    public void wechatAuthLogin(
            HttpServletResponse response,
            @RequestParam("code") String code,
            @RequestParam("url") String url,
            @RequestParam("appId") String appId,
            @RequestParam(Constant.PLATFORM) String platform,
            @RequestParam(Constant.VERSION) String version) throws IOException {
        if (!Util.isNullOrEmpty(code)) {
            //读取应用微信配置信息
            JSONObject jsonObject = appConfigRpc.findByCategoryCode(appId, "WEIXIN");
            
            String wechatAppId = ApiConfigKit.getAppId();
            if (!wechatAppId.equals(jsonObject.getString("WECHAT_APP_ID"))) {
                ApiConfigKit.setThreadLocalAppId(jsonObject.getString("WECHAT_APP_ID"));
                // AccessTokenApi.refreshAccessToken();
            }
            
            System.out.println(appId);

            SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(jsonObject.getString("WECHAT_APP_ID"), jsonObject.getString("WECHAT_APP_SECRET"), code);

            System.out.println(snsAccessToken.getJson());

            String wechatOpenId = snsAccessToken.getOpenid();
            String wechatUnionId = snsAccessToken.getUnionid();
            String systemRequestUserId = "";

            ApiResult apiResult = SnsApi.getUserInfo(snsAccessToken.getAccessToken(), wechatOpenId);

            System.out.println(apiResult.getJson());

            UserWechat userWechat = new UserWechat();
            userWechat.setAppId(appId);
            userWechat.setWechatOpenId(wechatOpenId);
            String wechatNickName = apiResult.getStr("nickname");
            String wechatHeadImgUrl = apiResult.getStr("headimgurl");
            String wechatSex = apiResult.getStr("sex");
            String wechatCountry = apiResult.getStr("country");
            String wechatProvince = apiResult.getStr("province");
            String wechatCity = apiResult.getStr("city");
            String wehchatPrivilege = apiResult.getStr("privilege");
            
            if (Util.isNullOrEmpty(wechatUnionId)) {
                wechatUnionId = "";
            }
            userWechat.setWechatUnionId(wechatUnionId);
            if (Util.isNullOrEmpty(wechatNickName)) {
                wechatNickName = "";
            }
            userWechat.setWechatNickName(wechatNickName);
            if (Util.isNullOrEmpty(wechatHeadImgUrl)) {
                wechatHeadImgUrl = "";
            }
            userWechat.setWechatHeadImgUrl(wechatHeadImgUrl);
            if (Util.isNullOrEmpty(wechatSex)) {
                wechatSex = "" ;
            }
            userWechat.setWechatSex(wechatSex);
            if (Util.isNullOrEmpty(wechatCountry)) {
                wechatCountry = "";
            }
            userWechat.setWechatCountry(wechatCountry);
            if (Util.isNullOrEmpty(wechatProvince)) {
                wechatProvince = "";
            }
            userWechat.setWechatProvince(wechatProvince);
            if (Util.isNullOrEmpty(wechatCity)) {
                wechatCity = "" ;
            }
            userWechat.setWechatCity(wechatCity);
            if (Util.isNullOrEmpty(wehchatPrivilege)) {
                wehchatPrivilege = "";
            }
            userWechat.setWehchatPrivilege(wehchatPrivilege);

            String userId = "";
            
            UserView userView = userRpc.findByUserWechatV1(appId, UserType.MEMBER.getKey(), userWechat.getWechatOpenId(), userWechat.getWechatUnionId());
    
            if (userView == null) {
                String memberId = Util.getRandomUUID();
                userId = Util.getRandomUUID();
    
                Member bean = new Member();
                bean.setAppId(appId);
                bean.setMemberId(memberId);
                bean.setUserId(userId);
                bean.setMemberIsRecommed(false);
                bean.setMemberIsTop(false);
                bean.setMemberTopEndTime(new Date());
                bean.setMemberTopLevel(0);
    
                Member result = memberService.save(bean, memberId, bean.getSystemRequestUserId());
//                        bean, memberId, systemRequestUserId
    
                if (result != null) {
                    throw new RuntimeException("保存不成功");
                }
//    
//                String fileId = fileRpc.downloadWechatHeadImgToNativeV1(appId, userId, userWechat.getWechatHeadImgUrl());
//                userWechat.setWechatHeadImgFileId(fileId);
////                        isSave = userRpc.saveUserWechatV1(appId, userId, memberId, UserType.MEMBER.getKey(), userWechat, systemRequestUserId);
//    //
////                        if (!isSave) {
////                            throw new BusinessException("保存不成功");
////                        }
//            } else {
//                userId = user.getUserId();
//    
//                String userWechatHeadImgUrl = (String) user.get;
//    
//                if (bean == null || userWechat.getWechatHeadImgUrl().equals(bean.getWechatHeadImgUrl())) {
//                    String fileId = fileRpc.downloadWechatHeadImgToNativeV1(appId, userId, userWechat.getWechatHeadImgUrl());
//    
//                    userWechat.setWechatHeadImgFileId(fileId);
//                    Boolean isUpdate = userRpc.updateUserWechatV1(userId, userWechat, systemRequestUserId);
//    
//                    if (!isUpdate) {
//                        throw new BusinessException("更新不成功");
//                    }
//                }
//    
//            }
            }
            String token = "";
            try {
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.YEAR, 1);
    
                JSONObject result1 = new JSONObject();
                result1.put(User.USER_ID, userId);
                result1.put(Constant.EXPIRE_TIME, calendar.getTime());
    
                token = AesUtil.aesEncrypt(result1.toJSONString(), Constant.PRIVATE_KEY);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("登录不成功");
            }

            System.out.println("openId:" + wechatOpenId);
            System.out.println("token:" + token);
            System.out.println("userNickName:" + wechatNickName);
            System.out.println("userAvatar:" + wechatHeadImgUrl);

            response.sendRedirect(url + "?&openId=" + wechatOpenId + "&token=" + token + "&userNickame=" + wechatNickName + "&userAvatar=" + wechatHeadImgUrl);

        }
                
    }
    
    @ApiOperation(value = "会员手机号码注册")
    @RequestMapping(value = "/member/mobile/v1/mobile/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> mobileRegisterV1() {
        UserAccount userAccountBean = getEntry(UserAccount.class);
        validateRequest(
                userAccountBean,
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
        
        if (!Util.isPhone(userAccountBean.getUserAccount())) {
            throw new BusinessException("手机号码格式不对");
        }
        
        Boolean isRegister = userRpc.checkUserAccountV1(userAccountBean.getAppId(), UserType.MEMBER.getKey(), userAccountBean.getUserAccount());
        
        if (isRegister) {
            throw new BusinessException("用户已注册");
        }
        
        // 验证验证码是否正确, 10分钟内有效
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -10);
        Boolean isCorrect = smsCaptchaRpc.checkCaptchaCodeV1(userAccountBean.getAppId(), userAccountBean.getUserAccount(), smsCaptcha.getSmsCaptchaCode(), DateUtil.getDateTimeString(calendar1.getTime()));
        if (!isCorrect) {
            throw new BusinessException("验证码错误");
        }
        
        Member member = new Member();
        
        String userId = Util.getRandomUUID();
        String memberId = Util.getRandomUUID();
        
        
        member.setAppId(userAccountBean.getAppId());
        member.setMemberId(memberId);
        member.setUserId(userId);
        member.setMemberIsRecommed(false);
        member.setMemberIsTop(false);
        member.setMemberTopEndTime(new Date());
        member.setMemberTopLevel(0);

        Member result = memberService.save(member, memberId, userAccountBean.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 发送会员手机注册用户消息
            String userAccount = userAccountBean.getUserAccount();
            // 默认用户昵称
            String userNickName = "WAWIPET" + userAccount.substring(userAccount.length() - 4);
            // 默认会员头像
            String userAvatarFileId = null;
            String userAvatarFilePath = null;
            MemberDefaultAvatarView memberDefaultAvatarView = memberDefaultAvatarService.randomFind(userAccountBean.getAppId());
            if (memberDefaultAvatarView != null) {
                userAvatarFileId = memberDefaultAvatarView.getUserAvatarFileId();
                userAvatarFilePath = memberDefaultAvatarView.getUserAvatarFilePath();
            }
            User user = new User();
            user.setAppId(userAccountBean.getAppId());
            user.setUserId(userId);
            user.setObjectId(memberId);
            user.setUserType(UserType.MEMBER.getKey());
            user.put(UserNickName.USER_NICK_NAME, userNickName);
            user.put(UserAccount.USER_ACCOUNT, userAccount);
            user.put(UserPassword.USER_PASSWORD, userPassword.getUserPassword());
            user.put(UserAvatar.USER_AVATAR_FILE_ID, userAvatarFileId);
            user.put(UserAvatar.USER_AVATAR_FILE_PATH, userAvatarFilePath);
            user.setSystemRequestUserId(userAccountBean.getSystemRequestUserId());
            sendMessage(user, UserRouter.USER_V1_MOBILE_REGISTER, userAccountBean.getAppId(), userAccountBean.getSystemRequestUserId());
            
            // 保存会员视图到MongoDB
            MemberView memberView = JSON.parseObject(result.toJSONString(), MemberView.class);
            memberView.setUserNickName(userNickName);
            memberView.setUserAvatarFilePath(userAvatarFilePath);
            
            memberService.save(memberView);
            
            // 发送会员保存信息
            sendMessage(memberView, MemberRouter.MEMBER_V1_SAVE, memberView.getAppId(), userAccountBean.getSystemRequestUserId());
            
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
        
        String theUserAccount = userAccount.getUserAccount();
        
        member.setAppId(userAccount.getAppId());
        member.setMemberId(memberId);
        member.setUserId(userId);
        member.setMemberIsRecommed(false);
        member.setMemberIsTop(false);
        member.setMemberTopEndTime(new Date());
        member.setMemberTopLevel(0);

        Member result = memberService.save(member,memberId,member.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 发送会员手机注册用户消息
            User user = new User();
            user.setAppId(userAccount.getAppId());
            user.setUserId(userId);
            user.setObjectId(memberId);
            user.setUserType(UserType.MEMBER.getKey());
//            user.put(UserNickName.USER_NICK_NAME, "wawi" + theUserAccount.substring(theUserAccount.length() - 4 ));
            user.put(UserAccount.USER_ACCOUNT, theUserAccount);
            user.put(UserPassword.USER_PASSWORD, userPassword.getUserPassword());
            user.setSystemRequestUserId(userAccount.getSystemRequestUserId());
            sendMessage(user, UserRouter.USER_V1_EMAIL_REGISTER, userAccount.getAppId(), userAccount.getSystemRequestUserId());

            // 保存会员视图到MongoDB
            MemberView memberView = JSON.parseObject(result.toJSONString(), MemberView.class);
            
            memberService.save(memberView);
            
            // 发送会员保存信息
            sendMessage(memberView, MemberRouter.MEMBER_V1_SAVE, memberView.getAppId(), userAccount.getSystemRequestUserId());
            
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
        
        // TODO 应改成发消息
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

        if (result) {
            // 发送会员签名更新消息
            sendMessage(memberSignature, MemberSignatureRouter.MEMBER_SIGNATURE_V1_UPDATE, memberSignature.getAppId(), memberSignature.getSystemRequestUserId());
        }
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
                MemberBackground.MEMBER_BACKGROUND_FILE_ID,
                MemberBackground.MEMBER_BACKGROUND_FILE_PATH
        );
        
        Boolean result = memberService.updateMemberBackground(memberBackground.getAppId(), memberBackground.getMemberId(), memberBackground.getMemberBackgroundFileId(), memberBackground.getMemberBackgroundFilePath(), memberBackground.getSystemRequestUserId());

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