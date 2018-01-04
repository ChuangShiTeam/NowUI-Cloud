package com.nowui.cloud.base.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.member.entity.MemberBookmark;
import com.nowui.cloud.base.member.service.MemberBookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员收藏管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员收藏", description = "会员收藏管理端接口管理")
@RestController
public class MemberBookmarkAdminController extends BaseController {

    @Autowired
    private MemberBookmarkService memberBookmarkService;

    @ApiOperation(value = "会员收藏列表")
    @RequestMapping(value = "/member/bookmark/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody MemberBookmark body) {
        validateRequest(
                body,
                MemberBookmark.APP_ID,
                MemberBookmark.MEMBER_ID,
                MemberBookmark.MEMBER_BOOKMARK_TITLE,
                MemberBookmark.PAGE_INDEX,
                MemberBookmark.PAGE_SIZE
        );

        Integer resultTotal = memberBookmarkService.adminCount(body.getAppId() , body.getMemberId(), body.getMemberBookmarkTitle());
        List<MemberBookmark> resultList = memberBookmarkService.adminList(body.getAppId(), body.getMemberId(), body.getMemberBookmarkTitle(), body.getM(), body.getN());

        validateResponse(
                MemberBookmark.MEMBER_BOOKMARK_ID,
                MemberBookmark.MEMBER_ID,
                MemberBookmark.USER_ID,
                MemberBookmark.OBJECT_ID,
                MemberBookmark.MEMBER_BOOKMARK_TITLE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员收藏信息")
    @RequestMapping(value = "/member/bookmark/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody MemberBookmark body) {
        validateRequest(
                body,
                MemberBookmark.APP_ID,
                MemberBookmark.MEMBER_BOOKMARK_ID
        );

        MemberBookmark result = memberBookmarkService.find(body.getMemberBookmarkId());

        validateResponse(
                MemberBookmark.MEMBER_BOOKMARK_ID,
                MemberBookmark.MEMBER_ID,
                MemberBookmark.USER_ID,
                MemberBookmark.OBJECT_ID,
                MemberBookmark.MEMBER_BOOKMARK_TITLE
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员收藏")
    @RequestMapping(value = "/member/bookmark/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody MemberBookmark body) {
        validateRequest(
                body,
                MemberBookmark.APP_ID,
                MemberBookmark.MEMBER_ID,
                MemberBookmark.USER_ID,
                MemberBookmark.OBJECT_ID,
                MemberBookmark.MEMBER_BOOKMARK_TITLE
        );

        Boolean result = memberBookmarkService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员收藏")
    @RequestMapping(value = "/member/bookmark/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody MemberBookmark body) {
        validateRequest(
                body,
                MemberBookmark.MEMBER_BOOKMARK_ID,
                MemberBookmark.APP_ID,
                MemberBookmark.MEMBER_ID,
                MemberBookmark.USER_ID,
                MemberBookmark.OBJECT_ID,
                MemberBookmark.MEMBER_BOOKMARK_TITLE,
                MemberBookmark.SYSTEM_VERSION
        );

        Boolean result = memberBookmarkService.update(body, body.getMemberBookmarkId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员收藏")
    @RequestMapping(value = "/member/bookmark/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody MemberBookmark body) {
        validateRequest(
                body,
                MemberBookmark.MEMBER_BOOKMARK_ID,
                MemberBookmark.APP_ID,
                MemberBookmark.SYSTEM_VERSION
        );

        Boolean result = memberBookmarkService.delete(body.getMemberBookmarkId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}