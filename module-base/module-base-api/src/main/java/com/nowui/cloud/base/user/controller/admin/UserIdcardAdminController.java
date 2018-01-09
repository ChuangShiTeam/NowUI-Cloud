package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.service.UserIdcardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户身份证管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户身份证", description = "用户身份证管理端接口管理")
@RestController
public class UserIdcardAdminController extends BaseController {

    @Autowired
    private UserIdcardService userIdcardService;

    @ApiOperation(value = "用户身份证列表")
    @RequestMapping(value = "/user/idcard/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody UserIdcard body) {
        validateRequest(
                body,
                UserIdcard.APP_ID,
                UserIdcard.USER_ID,
                UserIdcard.USER_NAME,
                UserIdcard.USER_IDCARD_NUMBER,
                UserIdcard.PAGE_INDEX,
                UserIdcard.PAGE_SIZE
        );

        Integer resultTotal = userIdcardService.countForAdmin(body.getAppId() , body.getUserId(), body.getUserName(), body.getUserIdcardNumber());
        List<UserIdcard> resultList = userIdcardService.listForAdmin(body.getAppId(), body.getUserId(), body.getUserName(), body.getUserIdcardNumber(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserIdcard.USER_IDCARD_ID,
                UserIdcard.USER_ID,
                UserIdcard.USER_NAME,
                UserIdcard.USER_IDCARD_NUMBER
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户身份证信息")
    @RequestMapping(value = "/user/idcard/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody UserIdcard body) {
        validateRequest(
                body,
                UserIdcard.APP_ID,
                UserIdcard.USER_IDCARD_ID
        );

        UserIdcard result = userIdcardService.find(body.getUserIdcardId());

        validateResponse(
                UserIdcard.USER_IDCARD_ID,
                UserIdcard.USER_ID,
                UserIdcard.USER_NAME,
                UserIdcard.USER_IDCARD_NUMBER
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户身份证")
    @RequestMapping(value = "/user/idcard/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody UserIdcard body) {
        validateRequest(
                body,
                UserIdcard.APP_ID,
                UserIdcard.USER_ID,
                UserIdcard.USER_NAME,
                UserIdcard.USER_IDCARD_NUMBER
        );

        Boolean result = userIdcardService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户身份证")
    @RequestMapping(value = "/user/idcard/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody UserIdcard body) {
        validateRequest(
                body,
                UserIdcard.USER_IDCARD_ID,
                UserIdcard.APP_ID,
                UserIdcard.USER_ID,
                UserIdcard.USER_NAME,
                UserIdcard.USER_IDCARD_NUMBER,
                UserIdcard.SYSTEM_VERSION
        );

        Boolean result = userIdcardService.update(body, body.getUserIdcardId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户身份证")
    @RequestMapping(value = "/user/idcard/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody UserIdcard body) {
        validateRequest(
                body,
                UserIdcard.USER_IDCARD_ID,
                UserIdcard.APP_ID,
                UserIdcard.SYSTEM_VERSION
        );

        Boolean result = userIdcardService.delete(body.getUserIdcardId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}