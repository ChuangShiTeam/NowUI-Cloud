package com.nowui.cloud.member.member.controller.system;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
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
    public String wechatLoginV1(String appId, UserWechat userWechat, String systemRequestUserId) {
        
        String userId = "";
        
        User user = userRpc.fingByUserWechatV1(appId, UserType.MEMBER.getKey(), userWechat.getWechatOpenId(), userWechat.getWechatUnionId());
                
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
            
            String fileId = fileRpc.downloadWechatHeadImgToNativeV1(appId, userId, userWechat.getWechatHeadImgUrl());
            userWechat.setWechatHeadImgFileId(fileId);
            isSave = userRpc.saveUserWechatV1(appId, userId, memberId, UserType.MEMBER.getKey(), userWechat, systemRequestUserId);
            
            if (!isSave) {
                throw new RuntimeException("保存不成功");
            }
        } else {
            userId = user.getUserId();
            
            UserWechat bean = (UserWechat) user.get(User.USER_WECHAT);
            
            if (bean == null || userWechat.getWechatHeadImgUrl().equals(bean.getWechatHeadImgUrl())) {
                String fileId = fileRpc.downloadWechatHeadImgToNativeV1(appId, userId, userWechat.getWechatHeadImgUrl());

                userWechat.setWechatHeadImgFileId(fileId);
                Boolean isUpdate = userRpc.updateUserWechatV1(userId, userWechat, systemRequestUserId);
                
                if (!isUpdate) {
                    throw new RuntimeException("更新不成功");
                }
            }
            
        }

        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, userId);
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());

            return AesUtil.aesEncrypt(jsonObject.toJSONString(), Constant.PRIVATE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
    }

    @Override
    public Member findByUserIdV1(String userId) {
        Member member = memberService.findWithCacheUserByUserId(userId);
        
        User user = (User) member.get(Member.USER);
        
        if (user == null) {
            user = userRpc.findV1(userId);
            
            member.put(Member.USER, user);
        }
        return null;
    }

    @Override
    public List<Member> listByUserIdsV1(String userIds) {
        if (Util.isNullOrEmpty(userIds)) {
            return null;
        }
        List<String> userIdList = JSONArray.parseArray(userIds, String.class);
        
        List<Member> memberList = userIdList.stream().map(userId -> findByUserIdV1(userId)).collect(Collectors.toList());
        
        return memberList;
    }

}