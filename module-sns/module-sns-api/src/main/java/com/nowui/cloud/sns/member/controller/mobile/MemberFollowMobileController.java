package com.nowui.cloud.sns.member.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.sns.member.entity.MemberFollow;
import com.nowui.cloud.sns.member.service.MemberFollowService;
import com.nowui.cloud.sns.member.service.MemberHomepageService;
import com.nowui.cloud.sns.member.view.MemberFollowView;
import com.nowui.cloud.sns.member.view.MemberHomepageView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员关注移动端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员关注", description = "会员关注移动端接口管理")
@RestController
public class MemberFollowMobileController extends BaseController {
    
    @Autowired
    private MemberFollowService memberFollowService;
    
//    @Autowired
//    private MemberService memberService;
    
    @Autowired
    private MemberHomepageService memberHomepageService;
    

    @ApiOperation(value = "我的关注列表")
    @RequestMapping(value = "/sns/member/follow/mobile/v1/my/follow/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> myFollowlistV1() {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );

        List<MemberFollowView> resultList = memberFollowService.listByUserId(memberFollow.getSystemRequestUserId());
        
        validateResponse(
                MemberFollowView.SNS_MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "关注我的列表")
    @RequestMapping(value = "/sns/member/follow/mobile/v1/follow/me/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> followMelistV1() {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        List<MemberFollowView> resultList = memberFollowService.listByFollowUserId(memberFollow.getSystemRequestUserId());
        
        List<MemberFollowView> myFollowList = memberFollowService.listByUserId(memberFollow.getSystemRequestUserId());

        for (MemberFollowView result : resultList) {
            result.put(MemberFollowView.MEMBER_IS_FOLLOW, Util.isNullOrEmpty(myFollowList) ? false : myFollowList.stream().anyMatch(myFollow -> myFollow.getFollowUserId().equals(result.getUserId())));
		}
        
        validateResponse(
                MemberFollowView.SNS_MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME,
                MemberFollowView.MEMBER_IS_FOLLOW
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "他的关注列表")
    @RequestMapping(value = "/sns/member/follow/mobile/v1/follow/him/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> followHimlistV1() {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.MEMBER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );

        List<MemberFollowView> resultList = memberFollowService.listByMemberId(memberFollow.getMemberId());
        
        List<MemberFollowView> myFollowList = memberFollowService.listByUserId(memberFollow.getSystemRequestUserId());
        
        for (MemberFollowView result : resultList) {
            result.put(MemberFollowView.MEMBER_IS_FOLLOW, Util.isNullOrEmpty(myFollowList) ? false : myFollowList.stream().anyMatch(myFollow -> myFollow.getFollowUserId().equals(result.getFollowUserId())));
            result.put(MemberFollowView.MEMBER_IS_SELF, result.getFollowUserId().equals(memberFollow.getSystemRequestUserId()));
		}
        
        validateResponse(
                MemberFollowView.SNS_MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME,
                MemberFollowView.MEMBER_IS_FOLLOW,
                MemberFollowView.MEMBER_IS_SELF
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "关注他的列表")
    @RequestMapping(value = "/sns/member/follow/mobile/v1/him/follow/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> himFollowListV1(@RequestBody MemberFollow body) {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.MEMBER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        List<MemberFollowView> resultList = memberFollowService.listByFollowMemberId(memberFollow.getMemberId());
        
        List<MemberFollowView> myFollowList = memberFollowService.listByUserId(memberFollow.getSystemRequestUserId());
        
        for (MemberFollowView result : resultList) {
            result.put(MemberFollowView.MEMBER_IS_FOLLOW, Util.isNullOrEmpty(myFollowList) ? false : myFollowList.stream().anyMatch(myFollow -> myFollow.getFollowUserId().equals(result.getUserId())));
            result.put(MemberFollowView.MEMBER_IS_SELF, result.getFollowUserId().equals(memberFollow.getSystemRequestUserId()));
        }
        
        validateResponse(
                MemberFollowView.SNS_MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME,
                MemberFollowView.MEMBER_IS_FOLLOW,
                MemberFollowView.MEMBER_IS_SELF
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "新增会员关注")
    @RequestMapping(value = "/sns/member/follow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.APP_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        String appId = memberFollow.getAppId();
        
//        MemberView followMemberView = memberService.find(memberFollow.getFollowMemberId());
        MemberHomepageView otherMemberHomepageView = memberHomepageService.findByMemberId(appId, memberFollow.getFollowMemberId());
        
//        if (followMemberView == null) {
//            throw new RuntimeException("会员不存在");
//        }
        if (otherMemberHomepageView == null) {
            throw new RuntimeException("未找到对方会员账号,请稍后再试,或反馈");
        }
        
//        MemberView memberView = memberService.findByUserId(memberFollow.getSystemRequestUserId());
        MemberHomepageView selfMemberHomepageVIew = memberHomepageService.findByUserId(appId, memberFollow.getSystemRequestUserId());
        
//        if (memberView == null) {
//            throw new RuntimeException("会员不存在");
//        }
        if (selfMemberHomepageVIew == null) {
            throw new RuntimeException("没有找到您的会员账号,请稍后再试,或反馈");
        }
        
//        if (memberFollow.getSystemRequestUserId().equals(followMemberView.getUserId())) {
//            throw new BusinessException("不能关注自己");
//        }
        if (memberFollow.getSystemRequestUserId().equals(otherMemberHomepageView.getUserId())) {
            throw new BusinessException("不能关注自己");
        }

//        memberFollow.setUserId(memberView.getUserId());
//        memberFollow.setMemberId(memberView.getMemberId());
        memberFollow.setUserId(selfMemberHomepageVIew.getUserId());
        memberFollow.setMemberId(selfMemberHomepageVIew.getMemberId());
        
//        memberFollow.setFollowUserId(followMemberView.getUserId());
        memberFollow.setFollowUserId(otherMemberHomepageView.getUserId());

        MemberFollow result = memberFollowService.save(memberFollow, Util.getRandomUUID(), memberFollow.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 保存会员关注视图到MongoDB
            MemberFollowView memberFollowView = JSON.parseObject(result.toJSONString(), MemberFollowView.class);
//            memberFollowView.setUserAvatarFilePath(memberView.getUserAvatarFilePath());
//            memberFollowView.setUserNickName(memberView.getUserNickName());
            memberFollowView.setUserAvatarFilePath(selfMemberHomepageVIew.getUserAvatarFilePath());
            memberFollowView.setUserNickName(selfMemberHomepageVIew.getUserNickName());
            
//            memberFollowView.setFollowUserAvatarFilePath(followMemberView.getUserAvatarFilePath());
//            memberFollowView.setFollowUserNickName(followMemberView.getUserNickName());
            memberFollowView.setFollowUserAvatarFilePath(otherMemberHomepageView.getUserAvatarFilePath());
            memberFollowView.setFollowUserNickName(otherMemberHomepageView.getUserNickName());
            
            memberFollowService.save(memberFollowView);
            
            //会员主页视图修改 请求者 的关注数: 加1
            MemberHomepageView theRequestMemberHomepageView = memberHomepageService.findByUserId(appId, memberFollow.getSystemRequestUserId());
            Integer memberFollowCount = theRequestMemberHomepageView.getMemberFollowCount();
            Integer memberFollowSystemVersion = theRequestMemberHomepageView.getSystemVersion();
            theRequestMemberHomepageView.setSystemVersion(memberFollowSystemVersion + 1);
            theRequestMemberHomepageView.setMemberFollowCount(memberFollowCount + 1);
            memberHomepageService.update(theRequestMemberHomepageView);
            
            //会员主页视图修改 被关注者 的被关注数: 加1
            MemberHomepageView beFollowMemberHomepageView = memberHomepageService.findByMemberId(appId, memberFollow.getFollowMemberId());
            Integer memberBeFollowCount = beFollowMemberHomepageView.getMemberBeFollowCount();
            Integer beFollowSystemVersion = theRequestMemberHomepageView.getSystemVersion();
            theRequestMemberHomepageView.setSystemVersion(beFollowSystemVersion + 1);
            beFollowMemberHomepageView.setMemberBeFollowCount(memberBeFollowCount + 1);
            memberHomepageService.update(beFollowMemberHomepageView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "取消会员关注")
    @RequestMapping(value = "/sns/member/follow/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberFollow body) {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.APP_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        String appId = memberFollow.getAppId();
//        MemberView followMemberView = memberService.find(memberFollow.getFollowMemberId());
        MemberHomepageView otherMemberHomepageView = memberHomepageService.findByMemberId(appId, memberFollow.getFollowMemberId());
//        if (followMemberView == null) {
//            throw new RuntimeException("会员不存在");
//        }
        if (otherMemberHomepageView == null) {
            throw new RuntimeException("对方的账号没找到,请您稍后再试,或反馈");
        }
        
//        MemberView memberView = memberService.findByUserId(memberFollow.getSystemRequestUserId());
        MemberHomepageView selfMemberHomepageView = memberHomepageService.findByUserId(appId, memberFollow.getSystemRequestUserId());
//        if (memberView == null) {
//            throw new RuntimeException("会员不存在");
//        }
        if (selfMemberHomepageView == null) {
            throw new RuntimeException("您的账号未找到,请您稍后再试,或反馈");
        }
        
//        MemberFollowView memberFollowView = memberFollowService.findByUserIdAndFollowUserId(memberFollow.getSystemRequestUserId(), followMemberView.getUserId());
        MemberFollowView memberFollowView = memberFollowService.findByUserIdAndFollowUserId(memberFollow.getSystemRequestUserId(), otherMemberHomepageView.getUserId());

        if (memberFollowView == null) {
            throw new BusinessException("没有关注该会员");
        }
        
        MemberFollow result = memberFollowService.delete(memberFollowView.getSnsMemberFollowId(), memberFollow.getSystemRequestUserId(), memberFollowView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除会员关注视图
            memberFollowService.delete(memberFollowView);
            
            
            // 修改请求者的关注数: 减一
            MemberHomepageView theRequestMemberHomepageView = memberHomepageService.findByUserId(appId, memberFollow.getSystemRequestUserId());
            Integer memberFollowCount = theRequestMemberHomepageView.getMemberFollowCount();
            Integer theRequestSystemVersion = theRequestMemberHomepageView.getSystemVersion();
            theRequestMemberHomepageView.setSystemVersion(theRequestSystemVersion + 1);
            theRequestMemberHomepageView.setMemberFollowCount(memberFollowCount - 1);
            memberHomepageService.update(theRequestMemberHomepageView);
            
            // 会员主页视图修改 被取消关注者 的被关注数: 减一
            MemberHomepageView beFollowMemberHomepageView = memberHomepageService.findByMemberId(appId, memberFollow.getFollowMemberId());
            Integer memberBeFollowCount = beFollowMemberHomepageView.getMemberBeFollowCount();
            Integer beFollowSystemVersion = beFollowMemberHomepageView.getSystemVersion();
            beFollowMemberHomepageView.setSystemVersion(beFollowSystemVersion + 1);
            beFollowMemberHomepageView.setMemberBeFollowCount(memberBeFollowCount - 1);
            memberHomepageService.update(beFollowMemberHomepageView);
            
            success = true;
        }

        return renderJson(success);
    }

}