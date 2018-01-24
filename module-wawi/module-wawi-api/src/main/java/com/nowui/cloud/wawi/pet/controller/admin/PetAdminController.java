package com.nowui.cloud.wawi.pet.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.pet.entity.Pet;
import com.nowui.cloud.wawi.pet.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 宠物管理端控制器
 *
 * @author hucy
 *
 * 2018-01-21
 */
@Api(value = "宠物", description = "宠物管理端接口管理")
@RestController
public class PetAdminController extends BaseController {

    @Autowired
    private PetService petService;

    @ApiOperation(value = "宠物列表")
    @RequestMapping(value = "/pet/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Pet petEntity = getEntry(Pet.class);

        validateRequest(
                petEntity,
                Pet.APP_ID,
                Pet.PET_CATEGORY_ID,
                Pet.PET_NAME,
                Pet.PAGE_INDEX,
                Pet.PAGE_SIZE
        );

        Integer resultTotal = petService.countForAdmin(petEntity.getAppId() , petEntity.getPetCategoryId(), petEntity.getPetName());
        List<Pet> resultList = petService.listForAdmin(petEntity.getAppId(), petEntity.getPetCategoryId(), petEntity.getPetName(), petEntity.getPageIndex(), petEntity.getPageSize());

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
    @RequestMapping(value = "/pet/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(value = "/pet/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

        Boolean result = petService.save(petEntity, Util.getRandomUUID(), petEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改宠物")
    @RequestMapping(value = "/pet/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(value = "/pet/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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