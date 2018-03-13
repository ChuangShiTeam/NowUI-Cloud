package com.nowui.cloud.wawi.wawi.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.wawi.entity.MemberVisitForum;
import com.nowui.cloud.wawi.wawi.router.MemberVisitForumRouter;
import com.nowui.cloud.wawi.wawi.service.MemberVisitForumService;
import com.nowui.cloud.wawi.wawi.view.MemberVisitForumView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员访问圈子管理端控制器
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Api(value = "会员访问圈子", description = "会员访问圈子管理端接口管理")
@RestController
public class MemberVisitForumAdminController extends BaseController {

    @Autowired
    private MemberVisitForumService memberVisitForumService;

    @ApiOperation(value = "会员访问圈子列表")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        MemberVisitForum memberVisitForumEntity = getEntry(MemberVisitForum.class);

        validateRequest(
                memberVisitForumEntity,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID,
                MemberVisitForum.PAGE_INDEX,
                MemberVisitForum.PAGE_SIZE
        );

        Integer resultTotal = memberVisitForumService.countForAdmin(memberVisitForumEntity.getAppId() , memberVisitForumEntity.getMemberId(), memberVisitForumEntity.getUserId());
        List<MemberVisitForum> resultList = memberVisitForumService.listForAdmin(memberVisitForumEntity.getAppId(), memberVisitForumEntity.getMemberId(), memberVisitForumEntity.getUserId(), memberVisitForumEntity.getPageIndex(), memberVisitForumEntity.getPageSize());

        validateResponse(
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员访问圈子信息")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        MemberVisitForum memberVisitForumEntity = getEntry(MemberVisitForum.class);

        validateRequest(
                memberVisitForumEntity,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_VISIT_FORUM_ID
        );

        MemberVisitForumView result = memberVisitForumService.find(memberVisitForumEntity.getMemberVisitForumId());

        validateResponse(
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员访问圈子")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        MemberVisitForum memberVisitForumEntity = getEntry(MemberVisitForum.class);

        validateRequest(
                memberVisitForumEntity,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID
        );

        String memberVisitForumId = Util.getRandomUUID();

        MemberVisitForum result = memberVisitForumService.save(memberVisitForumEntity, memberVisitForumId, memberVisitForumEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_SAVE, memberVisitForumEntity.getAppId(), memberVisitForumEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改会员访问圈子")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        MemberVisitForum memberVisitForumEntity = getEntry(MemberVisitForum.class);

        validateRequest(
                memberVisitForumEntity,
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID,
                MemberVisitForum.SYSTEM_VERSION
        );

        MemberVisitForum result = memberVisitForumService.update(memberVisitForumEntity, memberVisitForumEntity.getMemberVisitForumId(), memberVisitForumEntity.getSystemRequestUserId(), memberVisitForumEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_UPDATE, memberVisitForumEntity.getAppId(), memberVisitForumEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除会员访问圈子")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        MemberVisitForum memberVisitForumEntity = getEntry(MemberVisitForum.class);

        validateRequest(
                memberVisitForumEntity,
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.APP_ID,
                MemberVisitForum.SYSTEM_VERSION
        );

        MemberVisitForum result = memberVisitForumService.delete(memberVisitForumEntity.getMemberVisitForumId(), memberVisitForumEntity.getSystemRequestUserId(), memberVisitForumEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_DELETE, memberVisitForumEntity.getAppId(), memberVisitForumEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "会员访问圈子数据同步")
    @RequestMapping(value = "/pemberVisitForum/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<MemberVisitForum> pemberVisitForumList = memberVisitForumService.listByMysql();

        for (MemberVisitForum pemberVisitForum : pemberVisitForumList) {
            MemberVisitForumView pemberVisitForumView = new MemberVisitForumView();
            pemberVisitForumView.putAll(pemberVisitForum);

            memberVisitForumService.update(pemberVisitForumView);
        }

        return renderJson(true);
    }

}