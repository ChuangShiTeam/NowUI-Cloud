package com.nowui.cloud.wawi.wawi.controller.mobile;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.wawi.entity.MemberVisitForum;
import com.nowui.cloud.wawi.wawi.router.MemberVisitForumRouter;
import com.nowui.cloud.wawi.wawi.service.MemberVisitForumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员访问圈子移动端控制器
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Api(value = "会员访问圈子", description = "会员访问圈子移动端接口管理")
@RestController
public class MemberVisitForumMobileController extends BaseController {
	
	@Autowired
	private MemberVisitForumService memberVisitForumService;
	
	@Autowired
	private MemberRpc memberRpc;
	
	@ApiOperation(value = "会员访问圈子查询")
    @RequestMapping(value = "/wawi/member/visit/forum/mobile/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberVisitForum body) {
        validateRequest(
                body,
                MemberVisitForum.APP_ID,
                MemberVisitForum.SYSTEM_REQUEST_USER_ID
        );

        // 查询会员访问圈子记录
        MemberVisitForum memberVisitForum = memberVisitForumService.findByUserId(body.getSystemRequestUserId());

        Map<String, Object> result = new HashMap<>();
        if (Util.isNullOrEmpty(memberVisitForum)) {
        	// 保存会员访问圈子记录
        	Member member = memberRpc.findByUserIdV1(body.getSystemRequestUserId());
        	
        	body.setUserId(body.getSystemRequestUserId());
        	body.setMemberId(member.getMemberId());
        	
        	memberVisitForumService.save(body, Util.getRandomUUID(), body.getAppId(), MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_SAVE, body.getSystemRequestUserId());
        	
        	result.put("isVisit", "false");
        } else {
        	result.put("isVisit", "true");
        }
        
        validateResponse("isVisit");

        return renderJson(result);
    }

}