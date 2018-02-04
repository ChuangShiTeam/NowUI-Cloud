package com.nowui.cloud.cms.advertisement.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 广告移动端控制器
 *
 * @author marcus
 * <p>
 * 2017年12月26日
 */
@Api(value = "广告", description = "广告移动端接口管理")
@RestController
public class AdvertisementMobileController extends BaseController {

    @Autowired
    private AdvertisementService advertisementService;

    @ApiOperation(value = "首页广告轮播图列表")
    @RequestMapping(value = "/advertisement/mobile/v1/index/banner/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Advertisement body) {

        validateRequest(
                body,
                Advertisement.APP_ID
        );

        List<Advertisement> resultList = advertisementService.mobileList(body.getAppId(), "INDEX_BANNER");

        validateResponse(
                Advertisement.ADVERTISEMENT_TITLE,
                Advertisement.ADVERTISEMENT_IMAGE_ID,
                Advertisement.ADVERTISEMENT_LINK
        );

        return renderJson(resultList);
    }
}
