package com.nowui.cloud.member.member.controller.mobile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.service.MemberFollowService;
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
    private UserRpc userRpc;
    
    @Autowired
    private FileRpc fileRpc;

    @ApiOperation(value = "我的关注列表")
    @RequestMapping(value = "/member/follow/mobile/v1/my/follow/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> myFollowlistV1(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );

        List<MemberFollow> resultList = memberFollowService.listByUserId(body.getSystemRequestUserId());
        
        // 处理requestUser是否关注了列表中的粉丝
        for (MemberFollow memberFollow : resultList) {
			
        	MemberFollow myFollow = memberFollowService.findByUserIdAndFollowUserId(body.getSystemRequestUserId(), memberFollow.getFollowUserId());
        	
        	if (!Util.isNullOrEmpty(myFollow)) {
        		memberFollow.put(MemberFollow.MEMBER_IS_FOLLOW, true);
			}
        	else {
        		memberFollow.put(MemberFollow.MEMBER_IS_FOLLOW, false);
			}
		}
        
        
        //处理会员头像和昵称
        String userIds = Util.beanToFieldString(resultList, MemberFollow.FOLLOW_USER_ID);
        
        List<User> userList = userRpc.findsV1(userIds);
        
        if (!Util.isNullOrEmpty(userList)) {
            
            String fileIds = Util.beanToFieldString(userList, UserAvatar.USER_AVATAR);
            
            List<File> fileList = fileRpc.findsV1(fileIds);
            
            userList = Util.beanAddField(userList, UserAvatar.USER_AVATAR, fileList, File.FILE_PATH);
            
            resultList = Util.beanAddField(resultList, MemberFollow.FOLLOW_USER_ID, User.USER_ID, userList, File.FILE_PATH, User.USER_NICK_NAME);
        }
               
        validateResponse(
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.FOLLOW_USER_ID,
                File.FILE_PATH,
                User.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "关注我的列表")
    @RequestMapping(value = "/member/follow/mobile/v1/follow/me/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> followMelistV1(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        List<MemberFollow> resultList = memberFollowService.listByFollowUserId(body.getSystemRequestUserId());
        
        // 处理requestUser是否关注了列表中的粉丝
        for (MemberFollow memberFollow : resultList) {
			
        	MemberFollow FollowHim = memberFollowService.findByUserIdAndFollowUserId(body.getSystemRequestUserId(), memberFollow.getUserId());
        	
        	if (!Util.isNullOrEmpty(FollowHim)) {
        		memberFollow.put(MemberFollow.MEMBER_IS_FOLLOW, true);
			}
        	else {
        		memberFollow.put(MemberFollow.MEMBER_IS_FOLLOW, false);
			}
		}
        
        //处理会员头像和昵称
        String userIds = Util.beanToFieldString(resultList, MemberFollow.USER_ID);
        
        List<User> userList = userRpc.findsV1(userIds);
        
        if (!Util.isNullOrEmpty(userList)) {
            
            String fileIds = Util.beanToFieldString(userList, User.USER_AVATAR);;
            
            List<File> fileList = fileRpc.findsV1(fileIds);
            
            userList = Util.beanAddField(userList, User.USER_AVATAR, fileList, File.FILE_PATH);
            
            resultList = Util.beanAddField(resultList, MemberFollow.USER_ID, User.USER_ID, userList, File.FILE_PATH, User.USER_NICK_NAME);
        }
               
        validateResponse(
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.MEMBER_ID,
                MemberFollow.USER_ID,
                File.FILE_PATH,
                User.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "他的关注列表")
    @RequestMapping(value = "/member/follow/mobile/v1/follow/him/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> followHimlistV1(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.USER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );

        List<MemberFollow> resultList = memberFollowService.listByUserId(body.getUserId());
        
        for (MemberFollow memberFollow2 : resultList) {
        	// 处理关注他的人是否是自己
        	memberFollow2.put(MemberFollow.MEMBER_IS_SELF, memberFollow2.getFollowUserId().equals(body.getSystemRequestUserId()));
        	
		}
        
        //处理会员头像和昵称
        String userIds = Util.beanToFieldString(resultList, MemberFollow.FOLLOW_USER_ID);
        
        List<User> userList = userRpc.findsV1(userIds);
        
        if (!Util.isNullOrEmpty(userList)) {
            
            String fileIds = Util.beanToFieldString(userList, User.USER_AVATAR);;
            
            List<File> fileList = fileRpc.findsV1(fileIds);
            
            userList = Util.beanAddField(userList, User.USER_AVATAR, fileList, File.FILE_PATH);
            
            resultList = Util.beanAddField(resultList, MemberFollow.FOLLOW_USER_ID, User.USER_ID, userList, File.FILE_PATH, User.USER_NICK_NAME);
        }
        
        // 判断我是否关注过
        // 查询我的关注的列表
        List<MemberFollow> myFollowList = memberFollowService.listByUserId(body.getSystemRequestUserId());
        if (!Util.isNullOrEmpty(myFollowList)) {
            List<String> myFollowUserIdList = myFollowList.stream().map(memberFollow -> memberFollow.getFollowUserId()).collect(Collectors.toList());
            resultList.stream().forEach(result -> result.put(MemberFollow.MEMBER_IS_FOLLOW, myFollowUserIdList.contains(result.getFollowUserId())));
        }
               
        validateResponse(
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.FOLLOW_USER_ID,
                File.FILE_PATH,
                User.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                MemberFollow.MEMBER_IS_SELF
        );

        return renderJson(resultList);
    }
    
    
    
    @ApiOperation(value = "关注他的列表")
    @RequestMapping(value = "/member/follow/mobile/v1/him/follow/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> himFollowListV1(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.USER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        List<MemberFollow> resultList = memberFollowService.listByFollowUserId(body.getUserId());
        
        // 处理requestUser是否关注了列表中的粉丝
        for (MemberFollow memberFollow : resultList) {
			
        	MemberFollow FollowHim = memberFollowService.findByUserIdAndFollowUserId(body.getSystemRequestUserId(), memberFollow.getUserId());
        	
        	if (!Util.isNullOrEmpty(FollowHim)) {
        		memberFollow.put(MemberFollow.MEMBER_IS_FOLLOW, true);
			}
        	else {
        		memberFollow.put(MemberFollow.MEMBER_IS_FOLLOW, false);
			}
        	
        	// 处理关注他的人是否是自己
        	memberFollow.put(MemberFollow.MEMBER_IS_SELF, memberFollow.getUserId().equals(body.getSystemRequestUserId()));
        	
		}
        
        
        
        
        //处理会员头像和昵称
        String userIds = Util.beanToFieldString(resultList, MemberFollow.USER_ID);
        
        List<User> userList = userRpc.findsV1(userIds);
        
        if (!Util.isNullOrEmpty(userList)) {
            
            String fileIds = Util.beanToFieldString(userList, User.USER_AVATAR);;
            
            List<File> fileList = fileRpc.findsV1(fileIds);
            
            userList = Util.beanAddField(userList, User.USER_AVATAR, fileList, File.FILE_PATH);
            
            resultList = Util.beanAddField(resultList, MemberFollow.USER_ID, User.USER_ID, userList, File.FILE_PATH, User.USER_NICK_NAME);
        }
               
        validateResponse(
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.MEMBER_ID,
                MemberFollow.USER_ID,
                File.FILE_PATH,
                User.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                MemberFollow.MEMBER_IS_SELF
        );

        return renderJson(resultList);
    }
    
    
    
    
    @ApiOperation(value = "新增会员关注")
    @RequestMapping(value = "/member/follow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.APP_ID,
                MemberFollow.FOLLOW_USER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        if (body.getSystemRequestUserId().equals(body.getFollowUserId())) {
            throw new BusinessException("不能关注自己");
        }

        User user = userRpc.findV1(body.getSystemRequestUserId());
        
        User followUser = userRpc.findV1(body.getFollowUserId());
        
        body.setUserId(user.getUserId());
        body.setMemberId(user.getObjectId());
        body.setFollowMemberId(followUser.getObjectId());
        
        Boolean result = memberFollowService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "取消会员关注")
    @RequestMapping(value = "/member/follow/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.APP_ID,
                MemberFollow.FOLLOW_USER_ID,
                MemberFollow.SYSTEM_REQUEST_USER_ID
        );
        
        MemberFollow memberFollow = memberFollowService.findByUserIdAndFollowUserId(body.getSystemRequestUserId(), body.getFollowUserId());

        if (memberFollow == null) {
            throw new BusinessException("没有关注该会员");
        }
        Boolean result = memberFollowService.delete(memberFollow.getMemberFollowId(), body.getSystemRequestUserId(), memberFollow.getSystemVersion());

        return renderJson(result);
    }

}