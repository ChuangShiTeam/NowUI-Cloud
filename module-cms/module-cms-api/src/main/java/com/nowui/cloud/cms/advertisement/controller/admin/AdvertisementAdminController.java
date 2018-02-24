package com.nowui.cloud.cms.advertisement.controller.admin;

import java.util.List;
import java.util.Map;

import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.file.file.view.FileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.router.AdvertisementRouter;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
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
        AdvertisementView advertisementView = getEntry(AdvertisementView.class);

        validateRequest(
                advertisementView,
                AdvertisementView.APP_ID,
                AdvertisementView.ADVERTISEMENT_CATEGORY_CODE,
                AdvertisementView.ADVERTISEMENT_TITLE,
                AdvertisementView.PAGE_INDEX,
                AdvertisementView.PAGE_SIZE
        );

        Integer resultTotal = advertisementService.countForAdmin(advertisementView.getAppId(), advertisementView.getAdvertisementCategoryCode(), advertisementView.getAdvertisementTitle());
        List<AdvertisementView> resultList = advertisementService.listForAdmin(advertisementView.getAppId(), advertisementView.getAdvertisementCategoryCode(), advertisementView.getAdvertisementTitle(), advertisementView.getM(), advertisementView.getN());

        validateResponse(
                AdvertisementView.ADVERTISEMENT_ID,
                AdvertisementView.ADVERTISEMENT_CATEGORY_CODE,
                AdvertisementView.ADVERTISEMENT_TITLE,
                AdvertisementView.ADVERTISEMENT_CODE,
                AdvertisementView.ADVERTISEMENT_IMAGE,
                AdvertisementView.ADVERTISEMENT_IS_EFFICIENT,
                AdvertisementView.ADVERTISEMENT_LINK,
                AdvertisementView.ADVERTISEMENT_POSITION,
                AdvertisementView.ADVERTISEMENT_SORT
        );

        validateSecondResponse(AdvertisementView.ADVERTISEMENT_IMAGE, FileView.FILE_ID, FileView.FILE_PATH);

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询广告信息")
    @RequestMapping(value = "/advertisement/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        AdvertisementView advertisementView = getEntry(AdvertisementView.class);

        validateRequest(
                advertisementView,
                Advertisement.ADVERTISEMENT_ID);

        AdvertisementView result = advertisementService.find(advertisementView.getAdvertisementId());

        validateResponse(
                AdvertisementView.ADVERTISEMENT_ID,
                AdvertisementView.ADVERTISEMENT_CATEGORY_CODE,
                AdvertisementView.ADVERTISEMENT_CODE,
                AdvertisementView.ADVERTISEMENT_CONTENT,
                AdvertisementView.ADVERTISEMENT_IMAGE,
                AdvertisementView.ADVERTISEMENT_IS_EFFICIENT,
                AdvertisementView.ADVERTISEMENT_LINK,
                AdvertisementView.ADVERTISEMENT_POSITION,
                AdvertisementView.ADVERTISEMENT_SORT,
                AdvertisementView.ADVERTISEMENT_TITLE,
                AdvertisementView.SYSTEM_VERSION
        );

        validateSecondResponse(AdvertisementView.ADVERTISEMENT_IMAGE, FileView.FILE_ID, FileView.FILE_PATH);

        return renderJson(result);
    }

    @ApiOperation(value = "广告新增")
    @RequestMapping(value = "/advertisement/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
                Advertisement.APP_ID,
                Advertisement.ADVERTISEMENT_CATEGORY_CODE,
                Advertisement.ADVERTISEMENT_CODE,
                Advertisement.ADVERTISEMENT_CONTENT,
                Advertisement.ADVERTISEMENT_IMAGE,
                Advertisement.ADVERTISEMENT_IS_EFFICIENT,
                Advertisement.ADVERTISEMENT_LINK,
                Advertisement.ADVERTISEMENT_POSITION,
                Advertisement.ADVERTISEMENT_SORT,
                Advertisement.ADVERTISEMENT_TITLE
        );

        String advertisementId = Util.getRandomUUID();

        advertisementEntity.setAdvertisementImageId(advertisementEntity.getAdvertisementImage().getString(FileView.FILE_ID));

        Advertisement result = advertisementService.save(advertisementEntity, advertisementId, advertisementEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AdvertisementRouter.ADVERTISEMENT_V1_SAVE, advertisementEntity.getAppId(), advertisementEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "广告修改")
    @RequestMapping(value = "/advertisement/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
                Advertisement.ADVERTISEMENT_ID,
                Advertisement.APP_ID,
                Advertisement.ADVERTISEMENT_CATEGORY_CODE,
                Advertisement.ADVERTISEMENT_CODE,
                Advertisement.ADVERTISEMENT_CONTENT,
                Advertisement.ADVERTISEMENT_IMAGE,
                Advertisement.ADVERTISEMENT_IS_EFFICIENT,
                Advertisement.ADVERTISEMENT_LINK,
                Advertisement.ADVERTISEMENT_POSITION,
                Advertisement.ADVERTISEMENT_SORT,
                Advertisement.ADVERTISEMENT_TITLE,
                Advertisement.SYSTEM_VERSION
        );

        advertisementEntity.setAdvertisementImageId(advertisementEntity.getAdvertisementImage().getString(FileView.FILE_ID));

        Advertisement result = advertisementService.update(advertisementEntity, advertisementEntity.getAdvertisementId(), advertisementEntity.getSystemRequestUserId(), advertisementEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AdvertisementRouter.ADVERTISEMENT_V1_UPDATE, advertisementEntity.getAppId(), advertisementEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "广告删除")
    @RequestMapping(value = "/advertisement/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Advertisement advertisementEntity = getEntry(Advertisement.class);

        validateRequest(
                advertisementEntity,
                Advertisement.ADVERTISEMENT_ID,
                Advertisement.SYSTEM_VERSION
        );

        Advertisement result = advertisementService.delete(advertisementEntity.getAdvertisementId(), advertisementEntity.getSystemRequestUserId(), advertisementEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AdvertisementRouter.ADVERTISEMENT_V1_DELETE, advertisementEntity.getAppId(), advertisementEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "广告数据同步")
    @RequestMapping(value = "/advertisement/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Advertisement> advertisementList = advertisementService.listByMysql();

        for (Advertisement advertisement : advertisementList) {
            AdvertisementView advertisementView = new AdvertisementView();
            advertisementView.putAll(advertisement);

            File file = fileRpc.findByMysqlV1(advertisement.getAdvertisementImageId());
            advertisementView.setAdvertisementImage(file);

            advertisementService.update(advertisementView);
        }

        return renderJson(true);
    }


}
