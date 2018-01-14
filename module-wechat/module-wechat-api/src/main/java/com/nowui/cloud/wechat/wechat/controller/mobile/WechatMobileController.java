package com.nowui.cloud.wechat.wechat.controller.mobile;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;
import com.nowui.cloud.base.app.rpc.AppConfigRpc;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 微信移动端控制器
 * 
 * @author marcus
 *
 * 2018年1月10日
 */
@Api(value = "微信", description = "微信移动端接口管理")
@RestController
public class WechatMobileController extends BaseController {
    
    @Autowired
    private AppConfigRpc appConfigRpc;
    
    @Autowired
    private MemberRpc memberRpc;
    
    @ApiOperation(value = "微信授权登录")
    @GetMapping(value = "/wechat/auth")
    public void auth(
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
            String wehchatPrivilege = apiResult.getStr("Privilege");
            
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

            String token = memberRpc.wechatLoginV1(wechatAppId, userWechat, systemRequestUserId);

            System.out.println("openId:" + wechatOpenId);
            System.out.println("token:" + token);
            System.out.println("userNickName:" + wechatNickName);
            System.out.println("userAvatar:" + wechatHeadImgUrl);

            response.sendRedirect(url + "?&openId=" + wechatOpenId + "&token=" + token + "&userNickame=" + wechatNickName + "&userAvatar=" + wechatHeadImgUrl);

        }
                
    }

}
