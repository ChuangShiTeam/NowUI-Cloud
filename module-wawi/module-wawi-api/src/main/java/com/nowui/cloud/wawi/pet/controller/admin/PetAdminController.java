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
 * 2018-01-22
 */
@Api(value = "宠物", description = "宠物管理端接口管理")
@RestController
public class PetAdminController extends BaseController {

    @Autowired
    private PetService petService;

    @ApiOperation(value = "宠物列表")
    @RequestMapping(value = "/pet/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Pet body) {
        validateRequest(
                body,
                Pet.APP_ID,
                Pet.PET_NAME,
                Pet.PET_CATEGORY_ID,
                Pet.PAGE_INDEX,
                Pet.PAGE_SIZE
        );

        Integer resultTotal = petService.countForAdmin(body.getAppId() , body.getPetName(), body.getPetCategoryId());
        List<Pet> resultList = petService.listForAdmin(body.getAppId(), body.getPetName(), body.getPetCategoryId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                Pet.PET_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR,
                Pet.PET_CATEGORY_ID,
                Pet.SYSTEM_CREATE_USER_ID,
                Pet.SYSTEM_CREATE_TIME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "宠物信息")
    @RequestMapping(value = "/pet/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Pet body) {
        validateRequest(
                body,
                Pet.APP_ID,
                Pet.PET_ID
        );

        Pet result = petService.find(body.getPetId());

        validateResponse(
                Pet.PET_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR,
                Pet.PET_DESCRIPTION,
                Pet.PET_CATEGORY_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增宠物")
    @RequestMapping(value = "/pet/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Pet body) {
        validateRequest(
                body,
                Pet.APP_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR,
                Pet.PET_DESCRIPTION,
                Pet.PET_CATEGORY_ID
        );

        Boolean result = petService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改宠物")
    @RequestMapping(value = "/pet/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Pet body) {
        validateRequest(
                body,
                Pet.PET_ID,
                Pet.APP_ID,
                Pet.PET_NAME,
                Pet.PET_SEX,
                Pet.PET_BIRTHDAY,
                Pet.PET_AVATAR,
                Pet.PET_DESCRIPTION,
                Pet.PET_CATEGORY_ID,
                Pet.SYSTEM_VERSION
        );

        Boolean result = petService.update(body, body.getPetId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除宠物")
    @RequestMapping(value = "/pet/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Pet body) {
        validateRequest(
                body,
                Pet.PET_ID,
                Pet.APP_ID,
                Pet.SYSTEM_VERSION
        );

        Boolean result = petService.delete(body.getPetId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}