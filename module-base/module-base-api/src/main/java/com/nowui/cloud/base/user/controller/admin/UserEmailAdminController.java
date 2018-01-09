package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.service.UserEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户邮箱管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户邮箱", description = "用户邮箱管理端接口管理")
@RestController
public class UserEmailAdminController extends BaseController {

    @Autowired
    private UserEmailService userEmailService;

    @ApiOperation(value = "用户邮箱列表")
    @RequestMapping(value = "/user/email/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody UserEmail body) {
        validateRequest(
                body,
                UserEmail.APP_ID,
                UserEmail.USER_ID,
                UserEmail.USER_EMAIL,
                UserEmail.PAGE_INDEX,
                UserEmail.PAGE_SIZE
        );

        Integer resultTotal = userEmailService.countForAdmin(body.getAppId() , body.getUserId(), body.getUserEmail());
        List<UserEmail> resultList = userEmailService.listForAdmin(body.getAppId(), body.getUserId(), body.getUserEmail(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserEmail.USER_EMAIL_ID,
                UserEmail.USER_ID,
                UserEmail.USER_EMAIL
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户邮箱信息")
    @RequestMapping(value = "/user/email/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody UserEmail body) {
        validateRequest(
                body,
                UserEmail.APP_ID,
                UserEmail.USER_EMAIL_ID
        );

        UserEmail result = userEmailService.find(body.getUserEmailId());

        validateResponse(
                UserEmail.USER_EMAIL_ID,
                UserEmail.USER_ID,
                UserEmail.USER_EMAIL
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户邮箱")
    @RequestMapping(value = "/user/email/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody UserEmail body) {
        validateRequest(
                body,
                UserEmail.APP_ID,
                UserEmail.USER_ID,
                UserEmail.USER_EMAIL
        );

        Boolean result = userEmailService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户邮箱")
    @RequestMapping(value = "/user/email/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody UserEmail body) {
        validateRequest(
                body,
                UserEmail.USER_EMAIL_ID,
                UserEmail.APP_ID,
                UserEmail.USER_ID,
                UserEmail.USER_EMAIL,
                UserEmail.SYSTEM_VERSION
        );

        Boolean result = userEmailService.update(body, body.getUserEmailId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户邮箱")
    @RequestMapping(value = "/user/email/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody UserEmail body) {
        validateRequest(
                body,
                UserEmail.USER_EMAIL_ID,
                UserEmail.APP_ID,
                UserEmail.SYSTEM_VERSION
        );

        Boolean result = userEmailService.delete(body.getUserEmailId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}