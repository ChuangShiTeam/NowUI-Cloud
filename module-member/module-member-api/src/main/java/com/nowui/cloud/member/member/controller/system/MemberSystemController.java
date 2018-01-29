package com.nowui.cloud.member.member.controller.system;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.service.MemberFollowService;
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
    
    @Autowired
    private MemberFollowService memberFollowService;
    
    @Override
    public String wechatLoginV1(String appId, UserWechat userWechat, String systemRequestUserId) {
        
        String userId = "";
        
        User user = userRpc.findByUserWechatV1(appId, UserType.MEMBER.getKey(), userWechat.getWechatOpenId(), userWechat.getWechatUnionId());
                
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

            Boolean isSave = memberService.save(bean, memberId, systemRequestUserId);

            if (!isSave) {
                throw new BusinessException("保存不成功");
            }
            
            String fileId = fileRpc.downloadWechatHeadImgToNativeV1(appId, userId, userWechat.getWechatHeadImgUrl());
            userWechat.setWechatHeadImgFileId(fileId);
            isSave = userRpc.saveUserWechatV1(appId, userId, memberId, UserType.MEMBER.getKey(), userWechat, systemRequestUserId);
            
            if (!isSave) {
                throw new BusinessException("保存不成功");
            }
        } else {
            userId = user.getUserId();
            
            UserWechat bean = (UserWechat) user.get(User.USER_WECHAT);
            
            if (bean == null || userWechat.getWechatHeadImgUrl().equals(bean.getWechatHeadImgUrl())) {
                String fileId = fileRpc.downloadWechatHeadImgToNativeV1(appId, userId, userWechat.getWechatHeadImgUrl());

                userWechat.setWechatHeadImgFileId(fileId);
                Boolean isUpdate = userRpc.updateUserWechatV1(userId, userWechat, systemRequestUserId);
                
                if (!isUpdate) {
                    throw new BusinessException("更新不成功");
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
            throw new BusinessException("登录不成功");
        }
    }

    @Override
    public Member findByUserIdV1(String userId) {
        Member member = memberService.findByUserId(userId);
        
        if (member == null) {
            return null;
        }
        
        User user = (User) member.get(Member.MEMBER_USER);
        
        if (user == null) {
            user = userRpc.findV1(userId);
        }
        
        user.defaultKeep();
        
        member.putAll(user);
        
        return member;
    }
    
    @Override
    public Member findDetailByUserIdV1(String userId) {
        Member member = findByUserIdV1(userId);
        
        if (member == null) {
            return null;
        }
        
        // 处理用户头像
        String userAvatar = (String) member.get(UserAvatar.USER_AVATAR);
        if (!Util.isNullOrEmpty(userAvatar)) {
            File file = fileRpc.findV1(userAvatar);
            if (!Util.isNullOrEmpty(file)) {
                file.keep(File.FILE_ID, File.FILE_PATH);
            }
            member.put(UserAvatar.USER_AVATAR, file == null ? "" : file);
        }
        
        // 处理会员背景
        String memberBackground = (String) member.get(Member.MEMBER_BACKGROUND);
        if (!Util.isNullOrEmpty(memberBackground)) {
            File file = fileRpc.findV1(memberBackground);
            if (!Util.isNullOrEmpty(file)) {
                file.keep(File.FILE_ID, File.FILE_PATH);
            }
            member.put(Member.MEMBER_BACKGROUND, file == null ? "" : file);
        }
        
        return member;
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
    
    @Override
    public List<Member> nickNameAndAvatarAndIsFollowListV1(String userIds, String userId) {
        if (Util.isNullOrEmpty(userIds) || Util.isNullOrEmpty(userId)) {
            return null;
        }
        
        List<Member> memberList = listByUserIdsV1(userIds);
        
        if(memberList == null || memberList.size() == 0) {
            return null;
        }
        
        String fileIds = Util.beanToFieldString(memberList, User.USER_AVATAR);
        List<File> fileList = fileRpc.findsV1(fileIds);
        if (!Util.isNullOrEmpty(fileList)) {
            memberList = Util.beanReplaceField(memberList, User.USER_AVATAR, fileList, File.FILE_ID, File.FILE_PATH); 
        }
        
        for (Member member : memberList) {
            member.keep(User.USER_ID, UserNickName.USER_NICK_NAME, UserAvatar.USER_AVATAR);
        }
        
        // 查询我的关注列表
        List<MemberFollow> myFollowList = memberFollowService.listByUserId(userId);
        // 处理用户关注
        if (!Util.isNullOrEmpty(myFollowList)) {
            List<String> myFollowUserIdList = myFollowList.stream().map(memberFollow -> memberFollow.getFollowUserId()).collect(Collectors.toList());
            memberList.stream().forEach(member -> member.put(MemberFollow.MEMBER_IS_FOLLOW, myFollowUserIdList.contains(member.getUserId())));

        }
        return memberList;
    }
    
    @Override
    public Member nickNameAndAvatarAndIsFollowFindV1(String followUserId, String userId) {
        Member member = findDetailByUserIdV1(userId);
        
        if (member == null) {
            return null;
        }
        
        // 处理用户是否关注
        Boolean memberIsFollow = memberFollowService.checkIsFollow(userId, followUserId);
        
        member.put(MemberFollow.MEMBER_IS_FOLLOW, memberIsFollow);
        
        member.keep(User.USER_ID, UserNickName.USER_NICK_NAME, UserAvatar.USER_AVATAR, MemberFollow.MEMBER_IS_FOLLOW);
        
        return member;
    }

    @Override
    public List<Member> nickNameAndAvatarListV1(String userIds) {
        List<Member> memberList = listByUserIdsV1(userIds);
        
        if(memberList == null || memberList.size() == 0) {
            return null;
        }
        
        String fileIds = Util.beanToFieldString(memberList, User.USER_AVATAR);
        List<File> fileList = fileRpc.findsV1(fileIds);
        if (!Util.isNullOrEmpty(fileList)) {
            memberList = Util.beanReplaceField(memberList, User.USER_AVATAR, fileList, File.FILE_ID, File.FILE_PATH); 
        }
        
        for (Member member : memberList) {
            member.keep(User.USER_ID, UserNickName.USER_NICK_NAME, UserAvatar.USER_AVATAR);
        }
        
        return memberList;
    }

    @Override
    public Member nickNameAndAvatarFindV1(String userId) {
        
        Member member = findDetailByUserIdV1(userId);
        
        if (member == null) {
            return null;
        }
        
        member.keep(User.USER_ID, UserNickName.USER_NICK_NAME, UserAvatar.USER_AVATAR);
        
        return member;
    }

    @Override
    public Member nickNameAndAvatarAndBackgroundAndSignatureFind(String userId) {
        Member member = findDetailByUserIdV1(userId);
        
        if (member == null) {
            return null;
        }
        
        member.keep(
                User.USER_ID, 
                UserNickName.USER_NICK_NAME, 
                UserAvatar.USER_AVATAR, 
                MemberSignature.MEMBER_SIGNATURE, 
                MemberBackground.MEMBER_BACKGROUND
        );
        
        return member;
    }

    @Override
    public Member nickNameAndAvatarAndSignatureFind(String userId) {
        Member member = findDetailByUserIdV1(userId);
        
        if (member == null) {
            return null;
        }
        
        member.keep(
                User.USER_ID, 
                UserNickName.USER_NICK_NAME, 
                UserAvatar.USER_AVATAR, 
                MemberSignature.MEMBER_SIGNATURE
        );
        
        return member;
    }

}