package com.nowui.cloud.base.wechat.controller.mobile;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.sms.entity.enums.SmsCaptchaType;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
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
public class WechatController extends BaseController {
    
    @ApiOperation(value = "微信授权登录")
    @GetMapping(value = "/wechat/auth")
    public void auth(
            @RequestParam("code") String code,
            @RequestParam("url") String url,
            @RequestParam("appId") String appId,
            @RequestParam(Constant.PLATFORM) String platform,
            @RequestParam(Constant.VERSION) String version) {
        if (!Util.isNullOrEmpty(code)) {
            
        }
                
    }

}
