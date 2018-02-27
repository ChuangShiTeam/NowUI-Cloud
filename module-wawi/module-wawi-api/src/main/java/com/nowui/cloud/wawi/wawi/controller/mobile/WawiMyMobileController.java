package com.nowui.cloud.wawi.wawi.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserSex;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.rpc.MemberRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 哇伊我的模块移动端接口
 * 
 * @author marcus
 *
 * 2018年1月25日
 */
@Api(value = "哇伊我的", description = "哇伊我的移动端接口管理")
@RestController
public class WawiMyMobileController extends BaseController {
    
    @Autowired
    private MemberRpc memberRpc;
    
//    @ApiOperation(value = "哇伊我的首页列表")
//    @RequestMapping(value = "/wawi/mobile/v1/my/index", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> myIndexV1(@RequestBody App body) {
//        validateRequest(
//            body,
//            App.APP_ID,
//            App.SYSTEM_REQUEST_USER_ID
//        );
//
//        String userId = body.getSystemRequestUserId();
//
//        Member member = memberRpc.nickNameAndAvatarAndBackgroundAndSignatureFind(userId);
//
//        validateResponse(User.USER_NICK_NAME, User.USER_AVATAR, MemberBackground.MEMBER_BACKGROUND);
//
//        return renderJson(member);
//    }
    
//    @ApiOperation(value = "哇伊我的个人资料")
//    @RequestMapping(value = "/wawi/mobile/v1/my/info", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> myInfoV1(@RequestBody App body) {
//        validateRequest(
//            body,
//            App.APP_ID,
//            App.SYSTEM_REQUEST_USER_ID
//        );
//
//        String userId = body.getSystemRequestUserId();
//
//        Member member = memberRpc.findDetailByUserIdV1(userId);
//
//        validateResponse(
//                User.USER_NICK_NAME,
//                User.USER_AVATAR,
//                User.USER_SEX,
//                Member.MEMBER_SIGNATURE,
//                Member.MEMBER_ADDRESS_CITY,
//                Member.MEMBER_PREFERENCE_LANGUAGE
//        );
//
//        return renderJson(member);
//    }
    
//    @ApiOperation(value = "更新我的个人资料")
//    @RequestMapping(value = "/wawi/mobile/v1/my/info/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> myInfoUpdateV1(@RequestBody Member body){
//        validateRequest(
//            body,
//            Member.APP_ID,
//            Member.SYSTEM_REQUEST_USER_ID
//        );
//
//        UserAvatar userAvatar = getEntry(UserAvatar.class);
//        validateRequest(
//            userAvatar,
//            UserAvatar.USER_AVATAR
//        );
//
//        UserNickName userNickName = getEntry(UserNickName.class);
//        validateRequest(
//                userNickName,
//                UserNickName.USER_NICK_NAME
//        );
//
//        UserSex userIdcard = getEntry(UserSex.class);
//        validateRequest(
//                userIdcard,
//                UserSex.USER_SEX
//        );
//
//        MemberSignature memberSignature = getEntry(MemberSignature.class);
//        validateRequest(
//                memberSignature,
//                MemberSignature.MEMBER_SIGNATURE
//        );
//
//        MemberAddress memberAddress = getEntry(MemberAddress.class);
//        validateRequest(
//                memberAddress,
//                MemberAddress.MEMBER_ADDRESS_CITY
//        );
//
//        Boolean result = memberRpc.update(
//                body.getSystemRequestUserId(),
//                userAvatar.getUserAvatar(),
//                userNickName.getUserNickName(),
//                userIdcard.getUserSex(),
//                memberSignature.getMemberSignature(),
//                memberAddress.getMemberAddressCity(),
//                body.getSystemRequestUserId()
//        );
//
//        return renderJson(result);
//    }
    
    @ApiOperation(value = "哇伊我的首页消息提示")
    @RequestMapping(value = "/wawi/mobile/v1/my/index/message", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> myIndexMessageV1(@RequestBody App body) {
        validateRequest(
            body, 
            App.APP_ID,
            App.SYSTEM_REQUEST_USER_ID
        );

        // TODO 查询用户是否有新的消息
        Boolean hasNewMessage = false;
        return renderJson(hasNewMessage);
    }

}
