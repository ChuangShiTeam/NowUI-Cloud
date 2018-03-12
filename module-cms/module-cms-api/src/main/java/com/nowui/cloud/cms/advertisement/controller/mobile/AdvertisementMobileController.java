package com.nowui.cloud.cms.advertisement.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
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
    public Map<String, Object> indexBannerListV1() {
        AdvertisementView advertisementView = getEntry(AdvertisementView.class);
        validateRequest(
                advertisementView,
                AdvertisementView.APP_ID
        );

        List<AdvertisementView> resultList = advertisementService.listByCategoryCode(advertisementView.getAppId(), "INDEX_BANNER");

        validateResponse(
                AdvertisementView.ADVERTISEMENT_TITLE,
                AdvertisementView.ADVERTISEMENT_IMAGE_FILE_PATH,
                AdvertisementView.ADVERTISEMENT_LINK
        );

        return renderJson(resultList);
    }
    
    @ApiOperation(value = "首页热门话题广告轮播图列表")
    @RequestMapping(value = "/advertisement/mobile/v1/index/hot/topic/banner/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> indexHotTopicBannerlistV1() {
        AdvertisementView advertisementView = getEntry(AdvertisementView.class);
        validateRequest(
                advertisementView,
                AdvertisementView.APP_ID
        );

        List<AdvertisementView> resultList = advertisementService.listByCategoryCode(advertisementView.getAppId(), "INDEX_HOT_TOPIC_BANNER");

        validateResponse(
                AdvertisementView.ADVERTISEMENT_TITLE,
                AdvertisementView.ADVERTISEMENT_IMAGE_FILE_PATH,
                AdvertisementView.ADVERTISEMENT_LINK
        );

        return renderJson(resultList);
    }
}
