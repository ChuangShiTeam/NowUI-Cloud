package com.nowui.cloud.wawi.wawi.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberBackground;
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
    
    @Autowired
    private UserRpc useRpc;
    
    @ApiOperation(value = "哇伊我的首页列表")
    @RequestMapping(value = "/wawi/mobile/v1/my/index", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> myIndexV1(@RequestBody App body) {
        validateRequest(
            body, 
            App.APP_ID,
            App.SYSTEM_REQUEST_USER_ID
        );

        String userId = body.getSystemRequestUserId();
        
        Member member = memberRpc.nickNameAndAvatarAndBackgroundAndSignatureFind(userId);
        
        validateResponse(UserNickName.USER_NICK_NAME, UserAvatar.USER_AVATAR, MemberBackground.MEMBER_BACKGROUND);
        
        return renderJson(member);
    }
    
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
