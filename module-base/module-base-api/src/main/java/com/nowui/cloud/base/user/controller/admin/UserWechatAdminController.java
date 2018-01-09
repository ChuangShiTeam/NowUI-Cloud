package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.service.UserWechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户微信管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户微信", description = "用户微信管理端接口管理")
@RestController
public class UserWechatAdminController extends BaseController {

    @Autowired
    private UserWechatService userWechatService;

    @ApiOperation(value = "用户微信列表")
    @RequestMapping(value = "/user/wechat/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody UserWechat body) {
        validateRequest(
                body,
                UserWechat.APP_ID,
                UserWechat.USER_ID,
                UserWechat.WECHAT_NICK_NAME,
                UserWechat.PAGE_INDEX,
                UserWechat.PAGE_SIZE
        );

        Integer resultTotal = userWechatService.countForAdmin(body.getAppId() , body.getUserId(), body.getWechatNickName());
        List<UserWechat> resultList = userWechatService.listForAdmin(body.getAppId(), body.getUserId(), body.getWechatNickName(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserWechat.USER_WECHAT_ID,
                UserWechat.USER_ID,
                UserWechat.WECHAT_NICK_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户微信信息")
    @RequestMapping(value = "/user/wechat/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody UserWechat body) {
        validateRequest(
                body,
                UserWechat.APP_ID,
                UserWechat.USER_WECHAT_ID
        );

        UserWechat result = userWechatService.find(body.getUserWechatId());

        validateResponse(
                UserWechat.USER_WECHAT_ID,
                UserWechat.USER_ID,
                UserWechat.WECHAT_OPEN_ID,
                UserWechat.WECHAT_UNION_ID,
                UserWechat.WECHAT_NICK_NAME,
                UserWechat.WECHAT_SEX,
                UserWechat.WECHAT_COUNTRY,
                UserWechat.WECHAT_PROVINCE,
                UserWechat.WECHAT_CITY,
                UserWechat.WECHAT_HEAD_IMG_URL,
                UserWechat.WEHCHAT_PRIVILEGE
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户微信")
    @RequestMapping(value = "/user/wechat/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody UserWechat body) {
        validateRequest(
                body,
                UserWechat.APP_ID,
                UserWechat.USER_ID,
                UserWechat.WECHAT_OPEN_ID,
                UserWechat.WECHAT_UNION_ID,
                UserWechat.WECHAT_NICK_NAME,
                UserWechat.WECHAT_SEX,
                UserWechat.WECHAT_COUNTRY,
                UserWechat.WECHAT_PROVINCE,
                UserWechat.WECHAT_CITY,
                UserWechat.WECHAT_HEAD_IMG_URL,
                UserWechat.WEHCHAT_PRIVILEGE
        );

        Boolean result = userWechatService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户微信")
    @RequestMapping(value = "/user/wechat/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody UserWechat body) {
        validateRequest(
                body,
                UserWechat.USER_WECHAT_ID,
                UserWechat.APP_ID,
                UserWechat.USER_ID,
                UserWechat.WECHAT_OPEN_ID,
                UserWechat.WECHAT_UNION_ID,
                UserWechat.WECHAT_NICK_NAME,
                UserWechat.WECHAT_SEX,
                UserWechat.WECHAT_COUNTRY,
                UserWechat.WECHAT_PROVINCE,
                UserWechat.WECHAT_CITY,
                UserWechat.WECHAT_HEAD_IMG_URL,
                UserWechat.WEHCHAT_PRIVILEGE,
                UserWechat.SYSTEM_VERSION
        );

        Boolean result = userWechatService.update(body, body.getUserWechatId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户微信")
    @RequestMapping(value = "/user/wechat/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody UserWechat body) {
        validateRequest(
                body,
                UserWechat.USER_WECHAT_ID,
                UserWechat.APP_ID,
                UserWechat.SYSTEM_VERSION
        );

        Boolean result = userWechatService.delete(body.getUserWechatId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}