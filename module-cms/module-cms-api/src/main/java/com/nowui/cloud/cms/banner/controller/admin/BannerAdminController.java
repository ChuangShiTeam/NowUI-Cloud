package com.nowui.cloud.cms.banner.controller.admin;
import com.nowui.cloud.cms.banner.entity.Banner;
import com.nowui.cloud.cms.banner.service.BannerService;
import com.nowui.cloud.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.rmi.CORBA.Util;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class BannerAdminController extends BaseController {
    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "广告查询列表")
    @RequestMapping(value = "/banner/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> home(@RequestBody Banner body) {
        validateRequest(body,"appId","bannerTitle","pageIndex", "pageSize");
        Integer resultTotal = bannerService.adminBannerCount(body.getAppId(),body.getBannerTitle());
        List<Banner> resultList = bannerService.adminBannerList(body.getAppId(),body.getBannerTitle(), body.getM(), body.getN());
        validateResponse("bannerId","bannerTitle");
        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "创建Banner")
    @RequestMapping(value = "/banner/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Banner body){
        validateRequest(body,"appId","bannerTitle","bannerCategoryCode","bannerPosition");
        body.setSystemUpdateTime(new Date());
        body.setSystemCreateTime(new Date());
        body.setBannerId(UUID.randomUUID().toString().replaceAll("-", ""));
        body.setAppId("fa4c004f46ff43d1b6f0bd5ac892f3e9");
        Boolean result = bannerService.save(body);
        return renderJson(result);
    }

    @ApiOperation(value = "根据bannerId获取Banner信息")
    @RequestMapping(value = "/banner/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Banner body){
        validateRequest(body,"appId","bannerId");
        Banner banner = bannerService.find(body.getBannerId());
        validateResponse();
        return renderJson(banner);
    }

    @ApiOperation(value = "更新Banner")
    @RequestMapping(value = "/banner/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Banner body){
        validateRequest(body,"appId","bannerTitle","bannerCategoryCode","bannerPosition","bannerId");
        body.setSystemUpdateTime(new Date());
        Boolean result = bannerService.update(body);
        return renderJson(result);
    }

    @ApiOperation(value = "删除Banner")
    @RequestMapping(value = "/banner/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Banner body){
        validateRequest(body,"bannerId","appId");
        Boolean result = bannerService.delete(body.getBannerId());
        validateResponse();
        return renderJson(result);
    }
}