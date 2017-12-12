package com.nowui.cloud.cms.banner.controller.admin;
import com.nowui.cloud.cms.banner.entity.Banner;
import com.nowui.cloud.cms.banner.service.BannerService;
import com.nowui.cloud.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class BannerController extends BaseController {
    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "广告查询列表")
    @RequestMapping(value = "/banner/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> home(String appId,String title,Integer pageIndex) {
        Integer pageSize = 10;
        Integer resultTotal = 0;
        List<Banner> resultList = bannerService.Query(appId, title, pageIndex, pageSize);
        validateResponse();
        return renderJson(resultTotal, resultList);
    }
}