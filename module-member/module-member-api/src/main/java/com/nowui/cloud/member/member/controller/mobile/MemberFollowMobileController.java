package com.nowui.cloud.member.member.controller.mobile;

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
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.service.MemberFollowService;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.view.MemberFollowView;
import com.nowui.cloud.member.member.view.MemberView;
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
    
    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "我的关注列表")
    @RequestMapping(value = "/member/follow/mobile/v1/my/follow/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> myFollowlistV1() {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );

        List<MemberFollowView> resultList = memberFollowService.listByUserId(memberFollow.getSystemRequestUserId());
        
        validateResponse(
                MemberFollowView.MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "关注我的列表")
    @RequestMapping(value = "/member/follow/mobile/v1/follow/me/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
                MemberFollowView.MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME,
                MemberFollowView.MEMBER_IS_FOLLOW
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "他的关注列表")
    @RequestMapping(value = "/member/follow/mobile/v1/follow/him/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
                MemberFollowView.MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME,
                MemberFollowView.MEMBER_IS_FOLLOW,
                MemberFollowView.MEMBER_IS_SELF
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "关注他的列表")
    @RequestMapping(value = "/member/follow/mobile/v1/him/follow/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
                MemberFollowView.MEMBER_FOLLOW_ID,
                MemberFollowView.FOLLOW_MEMBER_ID,
                MemberFollowView.USER_AVATAR_FILE_PATH,
                MemberFollowView.USER_NICK_NAME,
                MemberFollowView.MEMBER_IS_FOLLOW,
                MemberFollowView.MEMBER_IS_SELF
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "新增会员关注")
    @RequestMapping(value = "/member/follow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.APP_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        MemberView followMemberView = memberService.find(memberFollow.getFollowMemberId());
        if (followMemberView == null) {
            throw new RuntimeException("会员不存在");
        }
        
        MemberView memberView = memberService.findByUserId(memberFollow.getSystemRequestUserId());
        
        if (memberView == null) {
            throw new RuntimeException("会员不存在");
        }
        
        if (memberFollow.getSystemRequestUserId().equals(followMemberView.getUserId())) {
            throw new BusinessException("不能关注自己");
        }

        memberFollow.setUserId(memberView.getUserId());
        memberFollow.setMemberId(memberView.getMemberId());
        memberFollow.setFollowUserId(followMemberView.getUserId());

        MemberFollow result = memberFollowService.save(memberFollow, Util.getRandomUUID(), memberFollow.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 保存会员关注视图到MongoDB
            MemberFollowView memberFollowView = JSON.parseObject(result.toJSONString(), MemberFollowView.class);
            memberFollowView.setUserAvatarFilePath(memberView.getUserAvatarFilePath());
            memberFollowView.setUserNickName(memberView.getUserNickName());
            memberFollowView.setFollowUserAvatarFilePath(followMemberView.getUserAvatarFilePath());
            memberFollowView.setFollowUserNickName(followMemberView.getUserNickName());
            
            memberFollowService.save(memberFollowView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "取消会员关注")
    @RequestMapping(value = "/member/follow/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberFollow body) {
        MemberFollow memberFollow = getEntry(MemberFollow.class);
        validateRequest(
                memberFollow,
                MemberFollow.APP_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        MemberView followMemberView = memberService.find(memberFollow.getFollowMemberId());
        if (followMemberView == null) {
            throw new RuntimeException("会员不存在");
        }
        
        MemberView memberView = memberService.findByUserId(memberFollow.getSystemRequestUserId());
        
        if (memberView == null) {
            throw new RuntimeException("会员不存在");
        }
        
        Boolean memberIsFollow = memberFollowService.checkIsFollow(memberFollow.getSystemRequestUserId(), followMemberView.getUserId());

        if (memberIsFollow) {
            throw new BusinessException("没有关注该会员");
        }
        
        MemberFollow result = memberFollowService.delete(memberFollow.getMemberFollowId(), memberFollow.getSystemUpdateUserId(), memberFollow.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除会员关注视图
            MemberFollowView memberFollowView = JSON.parseObject(result.toJSONString(), MemberFollowView.class);
            memberFollowService.delete(memberFollowView);
            
            success = true;
        }

        return renderJson(success);
    }

}