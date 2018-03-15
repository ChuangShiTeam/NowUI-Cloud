package com.nowui.cloud.base.user.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	
	
	@ApiOperation(value = "用户数据同步")
    @RequestMapping(value = "/user/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<User> userList = userService.listByMysql();

        for (User user : userList) {
            UserView userView = new UserView();
            userView.copy(user);

            userService.saveOrUpdate(userView, userView.getUserId());
        }

        return renderJson(true);
    }


}