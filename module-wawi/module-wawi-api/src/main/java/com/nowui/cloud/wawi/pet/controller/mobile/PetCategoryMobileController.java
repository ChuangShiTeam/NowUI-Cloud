package com.nowui.cloud.wawi.pet.controller.mobile;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.wawi.pet.entity.Pet;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;
import com.nowui.cloud.wawi.pet.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * 宠物分类移动端控制器
 *
 * @author marcus
 *
 * 2018-01-24
 */
@Api(value = "宠物分类", description = "宠物分类移动端接口管理")
@RestController
public class PetCategoryMobileController extends BaseController {

    @Autowired
    private PetCategoryService petCategoryService;

    @ApiOperation(value = "获取宠物分类列表")
    @RequestMapping(value = "/wawi/pet/category/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> petCategoryListV1(@RequestBody PetCategory body) {
        validateRequest(
                body,
                PetCategory.APP_ID
        );

        List<PetCategory> resultList = petCategoryService.topList(PetCategory.APP_ID);

        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_IMAGE
        );

        return renderJson(resultList);
    }

    @ApiOperation(value = "获取宠物子分类")
    @RequestMapping(value = "/wawi/pet/category/mobile/v1/childCategoryList", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> petCategoryChildListV1(@RequestBody PetCategory body){

        validateRequest(
                body,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_ID
        );

        List<PetCategory> categories = petCategoryService.childrenCategoryList(PetCategory.APP_ID,PetCategory.PET_CATEGORY_ID);

        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_IMAGE
        );

        return renderJson(categories);
    }

}