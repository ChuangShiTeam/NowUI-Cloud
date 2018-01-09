package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.service.UserAvatarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户头像管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户头像", description = "用户头像管理端接口管理")
@RestController
public class UserAvatarAdminController extends BaseController {

    @Autowired
    private UserAvatarService userAvatarService;

    @ApiOperation(value = "用户头像列表")
    @RequestMapping(value = "/user/avatar/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody UserAvatar body) {
        validateRequest(
                body,
                UserAvatar.APP_ID,
                UserAvatar.USER_ID,
                UserAvatar.USER_AVATAR,
                UserAvatar.PAGE_INDEX,
                UserAvatar.PAGE_SIZE
        );

        Integer resultTotal = userAvatarService.countForAdmin(body.getAppId() , body.getUserId(), body.getUserAvatar());
        List<UserAvatar> resultList = userAvatarService.listForAdmin(body.getAppId(), body.getUserId(), body.getUserAvatar(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserAvatar.USER_AVATAR_ID,
                UserAvatar.USER_ID,
                UserAvatar.USER_AVATAR
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户头像信息")
    @RequestMapping(value = "/user/avatar/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody UserAvatar body) {
        validateRequest(
                body,
                UserAvatar.APP_ID,
                UserAvatar.USER_AVATAR_ID
        );

        UserAvatar result = userAvatarService.find(body.getUserAvatarId());

        validateResponse(
                UserAvatar.USER_AVATAR_ID,
                UserAvatar.USER_ID,
                UserAvatar.USER_AVATAR
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户头像")
    @RequestMapping(value = "/user/avatar/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody UserAvatar body) {
        validateRequest(
                body,
                UserAvatar.APP_ID,
                UserAvatar.USER_ID,
                UserAvatar.USER_AVATAR
        );

        Boolean result = userAvatarService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户头像")
    @RequestMapping(value = "/user/avatar/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody UserAvatar body) {
        validateRequest(
                body,
                UserAvatar.USER_AVATAR_ID,
                UserAvatar.APP_ID,
                UserAvatar.USER_ID,
                UserAvatar.USER_AVATAR,
                UserAvatar.SYSTEM_VERSION
        );

        Boolean result = userAvatarService.update(body, body.getUserAvatarId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户头像")
    @RequestMapping(value = "/user/avatar/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody UserAvatar body) {
        validateRequest(
                body,
                UserAvatar.USER_AVATAR_ID,
                UserAvatar.APP_ID,
                UserAvatar.SYSTEM_VERSION
        );

        Boolean result = userAvatarService.delete(body.getUserAvatarId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}