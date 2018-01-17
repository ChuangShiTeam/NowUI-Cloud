package com.nowui.cloud.wawi.pet.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 宠物分类管理端控制器
 *
 * @author marcus
 *
 * 2018-01-16
 */
@Api(value = "宠物分类", description = "宠物分类管理端接口管理")
@RestController
public class PetCategoryAdminController extends BaseController {

    @Autowired
    private PetCategoryService petCategoryService;

    @ApiOperation(value = "宠物分类列表")
    @RequestMapping(value = "/pet/category/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody PetCategory body) {
        validateRequest(
                body,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PAGE_INDEX,
                PetCategory.PAGE_SIZE
        );

        Integer resultTotal = petCategoryService.countForAdmin(body.getAppId() , body.getPetCategoryName(), body.getPetCategoryCode(), body.getPetCategoryImage());
        List<PetCategory> resultList = petCategoryService.listForAdmin(body.getAppId(), body.getPetCategoryName(), body.getPetCategoryCode(), body.getPetCategoryImage(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "宠物分类信息")
    @RequestMapping(value = "/pet/category/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody PetCategory body) {
        validateRequest(
                body,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_ID
        );

        PetCategory result = petCategoryService.find(body.getPetCategoryId());

        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_DESCRIPTION,
                PetCategory.PET_CATEGORY_SORT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增宠物分类")
    @RequestMapping(value = "/pet/category/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody PetCategory body) {
        validateRequest(
                body,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_DESCRIPTION,
                PetCategory.PET_CATEGORY_SORT
        );

        Boolean result = petCategoryService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改宠物分类")
    @RequestMapping(value = "/pet/category/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody PetCategory body) {
        validateRequest(
                body,
                PetCategory.PET_CATEGORY_ID,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_DESCRIPTION,
                PetCategory.PET_CATEGORY_SORT,
                PetCategory.SYSTEM_VERSION
        );

        Boolean result = petCategoryService.update(body, body.getPetCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除宠物分类")
    @RequestMapping(value = "/pet/category/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody PetCategory body) {
        validateRequest(
                body,
                PetCategory.PET_CATEGORY_ID,
                PetCategory.APP_ID,
                PetCategory.SYSTEM_VERSION
        );

        Boolean result = petCategoryService.delete(body.getPetCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}