package com.nowui.cloud.member.member.controller.system;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.util.AesUtil;
import com.nowui.cloud.util.Util;

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
    
    @Autowired
    private UserRpc userRpc;
    
    @Autowired
    private FileRpc fileRpc;

    @Override
    public String wechatLogin(String appId, UserWechat userWechat, String systemRequestUserId) {
        
        String userId = "";
        
        User user = userRpc.fingByUserWechat(appId, UserType.MEMBER.getKey(), userWechat.getWechatOpenId(), userWechat.getWechatUnionId());
                
        if (user == null) {
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
            bean.setMemberDescription("");

            Boolean isSave = memberService.save(bean, memberId, systemRequestUserId);

            if (!isSave) {
                throw new RuntimeException("保存不成功");
            }
            
            String fileId = fileRpc.downloadWechatHeadImgToNative(appId, userId, userWechat.getWechatHeadImgUrl());

            isSave = userRpc.saveUserWechat(appId, userId, memberId, UserType.MEMBER.getKey(), userWechat, systemRequestUserId);
            
            if (!isSave) {
                throw new RuntimeException("保存不成功");
            }
        } else {
            userId = user.getUserId();
            String fileId = fileRpc.downloadWechatHeadImgToNative(bean.getAppId(), userId, userWechat.getWechatHeadImgUrl());

            Boolean isUpdate = userRpc.updateUserWechat(userId, userWechat, systemRequestUserId);
            
            if (!isUpdate) {
                throw new RuntimeException("更新不成功");
            }
        }

        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUserId());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());

            return AesUtil.aesEncrypt(jsonObject.toJSONString(), Constant.PRIVATE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
    }

}