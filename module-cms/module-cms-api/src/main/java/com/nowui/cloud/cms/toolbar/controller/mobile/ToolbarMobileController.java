package com.nowui.cloud.cms.toolbar.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.cms.toolbar.view.ToolbarView;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 工具栏移动端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "工具栏", description = "工具栏接口移动端管理")
@RestController
public class ToolbarMobileController extends BaseController {

    @Autowired
    private ToolbarService toolbarService;

    @ApiOperation(value = "工具栏列表")
	@RequestMapping(value = "/toolbar/mobile/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> listV1() {
        ToolbarView toolbarView = getEntry(ToolbarView.class);
        
    	validateRequest(
	        toolbarView,
	        ToolbarView.APP_ID
    	);

    	List<ToolbarView> resultList = toolbarService.mobileList(toolbarView.getAppId());

    	validateResponse(
	        ToolbarView.TOOLBAR_ACTIVE_IMAGE_FILE_PATH,
	        ToolbarView.TOOLBAR_IMAGE_FILE_PATH,
	        ToolbarView.TOOLBAR_URL,
	        ToolbarView.TOOLBAR_NAME
    	);

    	return renderJson(resultList);
    }
}