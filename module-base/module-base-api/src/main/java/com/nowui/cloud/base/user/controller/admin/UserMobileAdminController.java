package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.service.UserMobileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户手机号码管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户手机号码", description = "用户手机号码管理端接口管理")
@RestController
public class UserMobileAdminController extends BaseController {

    @Autowired
    private UserMobileService userMobileService;

    @ApiOperation(value = "用户手机号码列表")
    @RequestMapping(value = "/user/mobile/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody UserMobile body) {
        validateRequest(
                body,
                UserMobile.APP_ID,
                UserMobile.USER_ID,
                UserMobile.USER_MOBILE,
                UserMobile.PAGE_INDEX,
                UserMobile.PAGE_SIZE
        );

        Integer resultTotal = userMobileService.adminCount(body.getAppId() , body.getUserId(), body.getUserMobile());
        List<UserMobile> resultList = userMobileService.adminList(body.getAppId(), body.getUserId(), body.getUserMobile(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserMobile.USER_MOBILE_ID,
                UserMobile.USER_ID,
                UserMobile.USER_MOBILE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户手机号码信息")
    @RequestMapping(value = "/user/mobile/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody UserMobile body) {
        validateRequest(
                body,
                UserMobile.APP_ID,
                UserMobile.USER_MOBILE_ID
        );

        UserMobile result = userMobileService.find(body.getUserMobileId());

        validateResponse(
                UserMobile.USER_MOBILE_ID,
                UserMobile.USER_ID,
                UserMobile.USER_MOBILE
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户手机号码")
    @RequestMapping(value = "/user/mobile/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody UserMobile body) {
        validateRequest(
                body,
                UserMobile.APP_ID,
                UserMobile.USER_ID,
                UserMobile.USER_MOBILE
        );

        Boolean result = userMobileService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户手机号码")
    @RequestMapping(value = "/user/mobile/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody UserMobile body) {
        validateRequest(
                body,
                UserMobile.USER_MOBILE_ID,
                UserMobile.APP_ID,
                UserMobile.USER_ID,
                UserMobile.USER_MOBILE,
                UserMobile.SYSTEM_VERSION
        );

        Boolean result = userMobileService.update(body, body.getUserMobileId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户手机号码")
    @RequestMapping(value = "/user/mobile/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody UserMobile body) {
        validateRequest(
                body,
                UserMobile.USER_MOBILE_ID,
                UserMobile.APP_ID,
                UserMobile.SYSTEM_VERSION
        );

        Boolean result = userMobileService.delete(body.getUserMobileId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}