package com.nowui.cloud.cms.advertisement.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.router.AdvertisementRouter;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
import com.nowui.cloud.cms.toolbar.router.ToolbarRouter;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 广告后台端控制器
 *
 * @author marcus
 * <p>
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
    @RequestMapping(value = "/advertisement/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
                Advertisement.APP_ID,
                Advertisement.ADEVERTISEMENT_CATEGORY_CODE,
                Advertisement.ADEVERTISEMENT_TITLE,
                Advertisement.PAGE_INDEX,
                Advertisement.PAGE_SIZE
        );

        Integer resultTotal = advertisementService.countForAdmin(advertisementEntity.getAppId(), advertisementEntity.getAdvertisementCategoryCode(), advertisementEntity.getAdvertisementTitle());
        List<Advertisement> resultList = advertisementService.listForAdmin(advertisementEntity.getAppId(), advertisementEntity.getAdvertisementCategoryCode(), advertisementEntity.getAdvertisementTitle(), advertisementEntity.getM(), advertisementEntity.getN());

        String fileIds = Util.beanToFieldString(resultList, Advertisement.ADEVERTISEMENT_IMAGE);
        List<File> fileList = fileRpc.findsV1(fileIds);

        resultList = Util.beanAddField(resultList, Advertisement.ADEVERTISEMENT_IMAGE, fileList, File.FILE_PATH);

        validateResponse(
                Advertisement.ADEVERTISEMENT_ID,
                Advertisement.ADEVERTISEMENT_CATEGORY_CODE,
                Advertisement.ADEVERTISEMENT_TITLE,
                Advertisement.ADEVERTISEMENT_CODE,
                Advertisement.ADEVERTISEMENT_IMAGE,
                File.FILE_PATH,
                Advertisement.ADEVERTISEMENT_IS_EFFICIENT,
                Advertisement.ADEVERTISEMENT_LINK,
                Advertisement.ADEVERTISEMENT_POSITION,
                Advertisement.ADEVERTISEMENT_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询广告信息")
    @RequestMapping(value = "/advertisement/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
                Advertisement.ADEVERTISEMENT_ID);

        AdvertisementView result = advertisementService.find(advertisementEntity.getAdvertisementId());

        File file = fileRpc.findV1(result.getAdvertisementImage());
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
    @RequestMapping(value = "/advertisement/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
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

        Boolean result = advertisementService.save(advertisementEntity, Util.getRandomUUID(), advertisementEntity.getAppId(), AdvertisementRouter.ADVERTISEMENT_V1_SAVE, advertisementEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "广告修改")
    @RequestMapping(value = "/advertisement/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
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

        Boolean result = advertisementService.update(advertisementEntity, advertisementEntity.getAdvertisementId(), advertisementEntity.getAppId(), AdvertisementRouter.ADVERTISEMENT_V1_UPDATE, advertisementEntity.getSystemRequestUserId(), advertisementEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "广告删除")
    @RequestMapping(value = "/advertisement/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
                Advertisement.ADEVERTISEMENT_ID,
                Advertisement.SYSTEM_VERSION
        );

        Boolean result = advertisementService.delete(advertisementEntity.getAdvertisementId() ,advertisementEntity.getAppId(), AdvertisementRouter.ADVERTISEMENT_V1_DELETE, advertisementEntity.getSystemRequestUserId(), advertisementEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "广告重建缓存")
    @RequestMapping(value = "/advertisement/admin/v1/replace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(advertisementEntity, Advertisement.ADEVERTISEMENT_ID);

        advertisementService.replace(advertisementEntity.getAdvertisementId());

        return renderJson(true);
    }


}
