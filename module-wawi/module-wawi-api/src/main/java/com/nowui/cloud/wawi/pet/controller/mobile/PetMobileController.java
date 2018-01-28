package com.nowui.cloud.wawi.pet.controller.mobile;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.pet.entity.Pet;
import com.nowui.cloud.wawi.pet.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 宠物移动端控制器
 *
 * @author hucy
 *
 * 2018-01-21
 */
@Api(value = "宠物", description = "宠物移动端接口管理")
@RestController
public class PetMobileController extends BaseController {

    @Autowired
    private PetService petService;

    @Autowired
    private FileRpc fileRpc;

    @ApiOperation(value = "我的宠物列表")
    @RequestMapping(value = "/wawi/pet/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> petListV1() {
        Pet petEntity = getEntry(Pet.class);
        validateRequest(
                petEntity,
                Pet.APP_ID,
                Pet.USER_ID
        );

        Integer resultTotal = petService.countByUserId(petEntity.getAppId() , petEntity.getUserId());
        List<Pet> resultList = petService.listByUserId(petEntity.getAppId(), petEntity.getUserId(), petEntity.getPageIndex(), petEntity.getPageSize());

        String fileIds = Util.beanToFieldString(resultList, Pet.PET_AVATAR);
        List<File> fileList = fileRpc.findsV1(fileIds);
        resultList = Util.beanReplaceField(resultList, Pet.PET_AVATAR, fileList, File.FILE_PATH);

        validateResponse(
                Pet.PET_ID,
                Pet.PET_CATEGORY_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "宠物信息")
    @RequestMapping(value = "/wawi/pet/mobile/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Pet petEntity = getEntry(Pet.class);

        validateRequest(
                petEntity,
                Pet.APP_ID,
                Pet.PET_ID
        );

        Pet result = petService.find(petEntity.getPetId());

        validateResponse(
                Pet.PET_ID,
                Pet.USER_ID,
                Pet.PET_CATEGORY_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR,
                Pet.PET_DESCRIPTION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增宠物")
    @RequestMapping(value = "/wawi/pet/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Pet petEntity = getEntry(Pet.class);

        validateRequest(
                petEntity,
                Pet.APP_ID,
                Pet.USER_ID,
                Pet.PET_CATEGORY_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR,
                Pet.PET_DESCRIPTION
        );

        Boolean result = petService.save(petEntity, Util.getRandomUUID(), petEntity.getSystemCreateUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改宠物")
    @RequestMapping(value = "/wawi/pet/mobile/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Pet petEntity = getEntry(Pet.class);

        validateRequest(
                petEntity,
                Pet.PET_ID,
                Pet.APP_ID,
                Pet.USER_ID,
                Pet.PET_CATEGORY_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR,
                Pet.PET_DESCRIPTION,
                Pet.SYSTEM_VERSION
        );

        Boolean result = petService.update(petEntity, petEntity.getPetId(), petEntity.getSystemRequestUserId(), petEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除宠物")
    @RequestMapping(value = "/wawi/pet/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Pet petEntity = getEntry(Pet.class);

        validateRequest(
                petEntity,
                Pet.PET_ID,
                Pet.APP_ID,
                Pet.SYSTEM_VERSION
        );

        Boolean result = petService.delete(petEntity.getPetId(), petEntity.getSystemRequestUserId(), petEntity.getSystemVersion());

        return renderJson(result);
    }
}