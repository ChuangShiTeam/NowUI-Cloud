package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户账号管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户账号", description = "用户账号管理端接口管理")
@RestController
public class UserAccountAdminController extends BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @ApiOperation(value = "用户账号列表")
    @RequestMapping(value = "/user/account/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ID,
                UserAccount.USER_ACCOUNT,
                UserAccount.PAGE_INDEX,
                UserAccount.PAGE_SIZE
        );

        Integer resultTotal = userAccountService.countForAdmin(body.getAppId() , body.getUserId(), body.getUserAccount());
        List<UserAccount> resultList = userAccountService.listForAdmin(body.getAppId(), body.getUserId(), body.getUserAccount(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserAccount.USER_ACCOUNT_ID,
                UserAccount.USER_ID,
                UserAccount.USER_ACCOUNT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户账号信息")
    @RequestMapping(value = "/user/account/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ACCOUNT_ID
        );

        UserAccount result = userAccountService.find(body.getUserAccountId());

        validateResponse(
                UserAccount.USER_ACCOUNT_ID,
                UserAccount.USER_ID,
                UserAccount.USER_ACCOUNT,
                UserAccount.USER_PASSWORD
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户账号")
    @RequestMapping(value = "/user/account/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.APP_ID,
                UserAccount.USER_ID,
                UserAccount.USER_ACCOUNT,
                UserAccount.USER_PASSWORD
        );

        Boolean result = userAccountService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户账号")
    @RequestMapping(value = "/user/account/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.USER_ACCOUNT_ID,
                UserAccount.APP_ID,
                UserAccount.USER_ID,
                UserAccount.USER_ACCOUNT,
                UserAccount.USER_PASSWORD,
                UserAccount.SYSTEM_VERSION
        );

        Boolean result = userAccountService.update(body, body.getUserAccountId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户账号")
    @RequestMapping(value = "/user/account/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody UserAccount body) {
        validateRequest(
                body,
                UserAccount.USER_ACCOUNT_ID,
                UserAccount.APP_ID,
                UserAccount.SYSTEM_VERSION
        );

        Boolean result = userAccountService.delete(body.getUserAccountId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}