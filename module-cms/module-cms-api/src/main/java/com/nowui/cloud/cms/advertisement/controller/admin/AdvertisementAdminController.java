package com.nowui.cloud.cms.advertisement.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 广告后台端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "广告", description = "广告后台端接口管理")
@RestController
public class AdvertisementAdminController extends BaseController {
    
    @Autowired
    private AdvertisementService advertisementService;
    
    @Autowired
    private FileRpc fileRpc;
    
    @ApiOperation(value = "广告分页列表")
    @RequestMapping(value = "/advertisement/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Advertisement body) {
        validateRequest(
            body, 
            Advertisement.APP_ID, 
            Advertisement.ADEVERTISEMENT_CATEGORY_CODE, 
            Advertisement.ADEVERTISEMENT_TITLE, 
            Advertisement.PAGE_INDEX, 
            Advertisement.PAGE_SIZE
        );

        Integer resultTotal = advertisementService.adminCount(body.getAppId(), body.getAdvertisementCategoryCode(), body.getAdvertisementTitle());
        List<Advertisement> resultList = advertisementService.adminList(body.getAppId(), body.getAdvertisementCategoryCode(), body.getAdvertisementTitle(), body.getM(), body.getN());

        validateResponse(
            Advertisement.ADEVERTISEMENT_ID, 
            Advertisement.ADEVERTISEMENT_CATEGORY_CODE, 
            Advertisement.ADEVERTISEMENT_TITLE,
            Advertisement.ADEVERTISEMENT_CODE, 
            Advertisement.ADEVERTISEMENT_IMAGE, 
            Advertisement.ADEVERTISEMENT_IS_EFFICIENT, 
            Advertisement.ADEVERTISEMENT_LINK,
            Advertisement.ADEVERTISEMENT_POSITION
        );
        
        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询广告信息")
    @RequestMapping(value = "/advertisement/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Advertisement body) {
        validateRequest(body, Advertisement.ADEVERTISEMENT_ID);

        Advertisement result = advertisementService.find(body.getAdvertisementId());
        
        File file = fileRpc.find(result.getAdvertisementImage());
        file.keep(File.FILE_ID, File.FILE_PATH);
        result.put(Advertisement.ADEVERTISEMENT_IMAGE, file);


        validateResponse(
            Advertisement.ADEVERTISEMENT_ID, 
            Advertisement.ADEVERTISEMENT_CATEGORY_CODE, 
            Advertisement.ADEVERTISEMENT_CODE, 
            Advertisement.ADEVERTISEMENT_CONTENT, 
            Advertisement.ADEVERTISEMENT_IMAGE, 
            Advertisement.ADEVERTISEMENT_IS_EFFICIENT, 
            Advertisement.ADEVERTISEMENT_LINK, 
            Advertisement.ADEVERTISEMENT_POSITION, 
            Advertisement.ADEVERTISEMENT_SORT, 
            Advertisement.ADEVERTISEMENT_TITLE, 
            Advertisement.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "广告新增")
    @RequestMapping(value = "/advertisement/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Advertisement body) {
        validateRequest(
            body, 
            Advertisement.APP_ID, 
            Advertisement.ADEVERTISEMENT_CATEGORY_CODE, 
            Advertisement.ADEVERTISEMENT_CODE, 
            Advertisement.ADEVERTISEMENT_CONTENT, 
            Advertisement.ADEVERTISEMENT_IMAGE, 
            Advertisement.ADEVERTISEMENT_IS_EFFICIENT, 
            Advertisement.ADEVERTISEMENT_LINK, 
            Advertisement.ADEVERTISEMENT_POSITION, 
            Advertisement.ADEVERTISEMENT_SORT, 
            Advertisement.ADEVERTISEMENT_TITLE
        );

        Boolean result = advertisementService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "广告修改")
    @RequestMapping(value = "/advertisement/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Advertisement body) {
        validateRequest(
            body, 
            Advertisement.ADEVERTISEMENT_ID, 
            Advertisement.APP_ID, 
            Advertisement.ADEVERTISEMENT_CATEGORY_CODE, 
            Advertisement.ADEVERTISEMENT_CODE, 
            Advertisement.ADEVERTISEMENT_CONTENT, 
            Advertisement.ADEVERTISEMENT_IMAGE, 
            Advertisement.ADEVERTISEMENT_IS_EFFICIENT, 
            Advertisement.ADEVERTISEMENT_LINK, 
            Advertisement.ADEVERTISEMENT_POSITION, 
            Advertisement.ADEVERTISEMENT_SORT, 
            Advertisement.ADEVERTISEMENT_TITLE,
            Advertisement.SYSTEM_VERSION
        );

        Boolean result = advertisementService.update(body, body.getAdvertisementId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "广告删除")
    @RequestMapping(value = "/advertisement/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Advertisement body) {
        validateRequest(
            body, 
            Advertisement.ADEVERTISEMENT_ID, 
            Advertisement.SYSTEM_VERSION
        );

        Boolean result = advertisementService.delete(body.getAdvertisementId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }


}
