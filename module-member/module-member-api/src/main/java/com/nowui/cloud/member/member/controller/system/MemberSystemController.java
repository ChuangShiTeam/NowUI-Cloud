package com.nowui.cloud.member.member.controller.system;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.util.AesUtil;

import io.swagger.annotations.Api;

/**
 * 会员系统端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员", description = "会员系统端接口管理")
@RestController
public class MemberSystemController implements MemberRpc {
    
    @Autowired
    private MemberService memberService;

    @Override
    public String wechatLogin(String appId, UserWechat userWechat, String systemRequestUserId) {
        Member member = memberService.wechatSaveOrUpdate(appId, userWechat, systemRequestUserId);
        
        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, member.getUserId());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());

            return AesUtil.aesEncrypt(jsonObject.toJSONString(), Constant.PRIVATE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
    }

}