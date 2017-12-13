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

    @ApiOperation(value = "根据bannerId获取Banner信息")
    @RequestMapping(value = "/banner/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(String bannerId){
        Integer resultTotal = 0;
        Banner banner = bannerService.find(bannerId);
        validateResponse();
        return renderJson(resultTotal,banner);
    }

    @ApiOperation(value = "创建Banner")
    @RequestMapping(value = "/banner/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(Banner banner){
        Integer resultTotal = 0;
        Boolean success = bannerService.save(banner);
        validateResponse();
        return renderJson(resultTotal,success);
    }

    @ApiOperation(value = "更新Banner")
    @RequestMapping(value = "/banner/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(Banner banner){
        Integer resultTotal = 0;
        Boolean success = bannerService.update(banner);
        validateResponse();
        return renderJson(resultTotal,success);
    }

    @ApiOperation(value = "删除Banner")
    @RequestMapping(value = "/banner/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(String productId){
        Integer resultTotal = 0;
        Boolean success = bannerService.delete(productId);
        validateResponse();
        return renderJson(resultTotal,success);
    }

}