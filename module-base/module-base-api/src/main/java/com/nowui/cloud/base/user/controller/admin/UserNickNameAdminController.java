package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.service.UserNickNameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户昵称管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户昵称", description = "用户昵称管理端接口管理")
@RestController
public class UserNickNameAdminController extends BaseController {

    @Autowired
    private UserNickNameService userNickNameService;

    @ApiOperation(value = "用户昵称列表")
    @RequestMapping(value = "/user/nick/name/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody UserNickName body) {
        validateRequest(
                body,
                UserNickName.APP_ID,
                UserNickName.USER_ID,
                UserNickName.USER_NICK_NAME,
                UserNickName.PAGE_INDEX,
                UserNickName.PAGE_SIZE
        );

        Integer resultTotal = userNickNameService.adminCount(body.getAppId() , body.getUserId(), body.getUserNickName());
        List<UserNickName> resultList = userNickNameService.adminList(body.getAppId(), body.getUserId(), body.getUserNickName(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserNickName.USER_NICK_NAME_ID,
                UserNickName.USER_ID,
                UserNickName.USER_NICK_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户昵称信息")
    @RequestMapping(value = "/user/nick/name/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody UserNickName body) {
        validateRequest(
                body,
                UserNickName.APP_ID,
                UserNickName.USER_NICK_NAME_ID
        );

        UserNickName result = userNickNameService.find(body.getUserNickNameId());

        validateResponse(
                UserNickName.USER_NICK_NAME_ID,
                UserNickName.USER_ID,
                UserNickName.USER_NICK_NAME
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户昵称")
    @RequestMapping(value = "/user/nick/name/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody UserNickName body) {
        validateRequest(
                body,
                UserNickName.APP_ID,
                UserNickName.USER_ID,
                UserNickName.USER_NICK_NAME
        );

        Boolean result = userNickNameService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户昵称")
    @RequestMapping(value = "/user/nick/name/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody UserNickName body) {
        validateRequest(
                body,
                UserNickName.USER_NICK_NAME_ID,
                UserNickName.APP_ID,
                UserNickName.USER_ID,
                UserNickName.USER_NICK_NAME,
                UserNickName.SYSTEM_VERSION
        );

        Boolean result = userNickNameService.update(body, body.getUserNickNameId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户昵称")
    @RequestMapping(value = "/user/nick/name/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody UserNickName body) {
        validateRequest(
                body,
                UserNickName.USER_NICK_NAME_ID,
                UserNickName.APP_ID,
                UserNickName.SYSTEM_VERSION
        );

        Boolean result = userNickNameService.delete(body.getUserNickNameId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}