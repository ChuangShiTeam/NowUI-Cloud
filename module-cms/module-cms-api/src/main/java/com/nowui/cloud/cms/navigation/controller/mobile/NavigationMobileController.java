package com.nowui.cloud.cms.navigation.controller.mobile;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import com.nowui.cloud.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 导航栏移动端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "导航栏", description = "导航栏移动端接口管理")
@RestController
public class NavigationMobileController extends BaseController {
	
	@Autowired
	private NavigationService navigationService;
	
	@ApiOperation(value = "导航栏列表")
	@RequestMapping(value = "/navigation/mobile/v1/index/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> listV1(@RequestBody Navigation body) {
		
		validateRequest(
				body,
				Navigation.APP_ID
			);
		
		List<Navigation> resultList = navigationService.mobileList(body.getAppId(), "INDEX");

		validateResponse(
				Navigation.NAVIGATION_NAME,
				Navigation.NAVIGATION_IMAGE,
				Navigation.NAVIGATION_URL
			);

		return renderJson(resultList);
	}
}