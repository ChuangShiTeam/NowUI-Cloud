package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户", description = "用户管理端接口管理")
@RestController
public class UserAdminController extends BaseController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private FileRpc fileRpc;

    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/user/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID,
                User.USER_TYPE,
                User.USER_ACCOUNT,
                User.USER_NAME,
                User.USER_MOBILE,
                User.PAGE_INDEX,
                User.PAGE_SIZE
        );

        Integer resultTotal = userService.adminCount(body.getAppId() , body.getUserType(), body.getUserAccount(), body.getUserName(), body.getUserMobile());
        List<User> resultList = userService.adminList(body.getAppId(), body.getUserType(), body.getUserAccount(), body.getUserName(), body.getUserMobile(), body.getM(), body.getN());

        validateResponse(
                User.USER_ID,
                User.USER_TYPE,
                User.USER_ACCOUNT,
                User.USER_NICK_NAME,
                User.USER_NAME,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "/user/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID,
                User.USER_ID
        );

        User result = userService.find(body.getUserId());
        
        File file = fileRpc.find(result.getUserAvatar());
        file.keep(File.FILE_ID, File.FILE_PATH);
        result.put(User.USER_AVATAR, file);

        validateResponse(
                User.USER_ID,
                User.OBJECT_ID,
                User.USER_TYPE,
                User.USER_ACCOUNT,
                User.USER_PASSWORD,
                User.USER_NICK_NAME,
                User.USER_NAME,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR,
                User.WEIXIN_OPEN_ID,
                User.WEIXIN_UNION_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/user/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID,
                User.OBJECT_ID,
                User.USER_TYPE,
                User.USER_ACCOUNT,
                User.USER_PASSWORD,
                User.USER_NICK_NAME,
                User.USER_NAME,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR,
                User.WEIXIN_OPEN_ID,
                User.WEIXIN_UNION_ID
        );

        Boolean result = userService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户")
    @RequestMapping(value = "/user/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody User body) {
        validateRequest(
                body,
                User.USER_ID,
                User.APP_ID,
                User.OBJECT_ID,
                User.USER_TYPE,
                User.USER_ACCOUNT,
                User.USER_PASSWORD,
                User.USER_NICK_NAME,
                User.USER_NAME,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR,
                User.WEIXIN_OPEN_ID,
                User.WEIXIN_UNION_ID,
                User.SYSTEM_VERSION
        );

        Boolean result = userService.update(body, body.getUserId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/user/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody User body) {
        validateRequest(
                body,
                User.USER_ID,
                User.APP_ID,
                User.SYSTEM_VERSION
        );

        Boolean result = userService.delete(body.getUserId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}