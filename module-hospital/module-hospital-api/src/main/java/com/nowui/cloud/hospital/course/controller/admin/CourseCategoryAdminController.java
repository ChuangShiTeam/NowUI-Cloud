package com.nowui.cloud.hospital.course.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.hospital.course.entity.CourseCategory;
import com.nowui.cloud.hospital.course.view.CourseCategoryView;
import com.nowui.cloud.hospital.course.service.CourseCategoryService;
import com.nowui.cloud.view.CommonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;
import java.util.Map;

/**
 * 课程分类管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Api(value = "课程分类", description = "课程分类管理端接口管理")
@RestController
public class CourseCategoryAdminController extends BaseController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @ApiOperation(value = "课程分类列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_NAME, value = "课程分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_CODE, value = "课程分类编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_DESCRIPTION, value = "课程分类描述", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/course/category/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore CourseCategoryView courseCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCategoryView,
                CourseCategoryView.APP_ID,
                CourseCategoryView.COURSE_CATEGORY_NAME,
                CourseCategoryView.COURSE_CATEGORY_CODE,
                CourseCategoryView.COURSE_CATEGORY_DESCRIPTION
        );

        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = courseCategoryService.countForAdmin(courseCategoryView.getAppId(), courseCategoryView.getCourseCategoryName(), courseCategoryView.getCourseCategoryCode(), courseCategoryView.getCourseCategoryDescription());
        List<CourseCategoryView> resultList = courseCategoryService.listForAdmin(courseCategoryView.getAppId(), courseCategoryView.getCourseCategoryName(), courseCategoryView.getCourseCategoryCode(), courseCategoryView.getCourseCategoryDescription(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                CourseCategoryView.COURSE_CATEGORY_ID,
                CourseCategoryView.COURSE_CATEGORY_PARENT_ID,
                CourseCategoryView.COURSE_CATEGORY_PARENT_PATH,
                CourseCategoryView.COURSE_CATEGORY_NAME,
                CourseCategoryView.COURSE_CATEGORY_CODE,
                CourseCategoryView.COURSE_CATEGORY_IMAGE_FILE_ID,
                CourseCategoryView.COURSE_CATEGORY_DESCRIPTION,
                CourseCategoryView.COURSE_CATEGORY_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "课程分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_ID, value = "课程分类编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/category/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore CourseCategoryView courseCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCategoryView,
                CourseCategoryView.COURSE_CATEGORY_ID,
                CourseCategoryView.APP_ID
        );

        CourseCategoryView result = courseCategoryService.find(courseCategoryView.getCourseCategoryId(), courseCategoryView.getAppId());

        validateResponse(
                CourseCategoryView.COURSE_CATEGORY_ID,
            	CourseCategoryView.COURSE_CATEGORY_PARENT_ID,
            	CourseCategoryView.COURSE_CATEGORY_PARENT_PATH,
            	CourseCategoryView.COURSE_CATEGORY_NAME,
            	CourseCategoryView.COURSE_CATEGORY_CODE,
            	CourseCategoryView.COURSE_CATEGORY_IMAGE_FILE_ID,
            	CourseCategoryView.COURSE_CATEGORY_DESCRIPTION,
            	CourseCategoryView.COURSE_CATEGORY_SORT,
                CourseCategoryView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "课程分类新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_PARENT_ID, value = "课程父分类编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_PARENT_PATH, value = "课程父分类路径", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_NAME, value = "课程分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_CODE, value = "课程分类编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_IMAGE_FILE_ID, value = "课程分类图片文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_DESCRIPTION, value = "课程分类描述", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_SORT, value = "课程分类排序", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/category/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore CourseCategory courseCategory, @ApiIgnore CourseCategoryView courseCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCategoryView,
                CourseCategoryView.APP_ID,
                CourseCategoryView.COURSE_CATEGORY_PARENT_ID,
                CourseCategoryView.COURSE_CATEGORY_PARENT_PATH,
                CourseCategoryView.COURSE_CATEGORY_NAME,
                CourseCategoryView.COURSE_CATEGORY_CODE,
                CourseCategoryView.COURSE_CATEGORY_IMAGE_FILE_ID,
                CourseCategoryView.COURSE_CATEGORY_DESCRIPTION,
                CourseCategoryView.COURSE_CATEGORY_SORT
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String courseCategoryId = Util.getRandomUUID();

        CourseCategory result = courseCategoryService.save(courseCategory, courseCategoryId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            courseCategoryView.copy(result);

            courseCategoryService.save(courseCategoryView);

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程分类修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_PARENT_ID, value = "课程父分类编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_PARENT_PATH, value = "课程父分类路径", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_NAME, value = "课程分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_CODE, value = "课程分类编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_IMAGE_FILE_ID, value = "课程分类图片文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_DESCRIPTION, value = "课程分类描述", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.COURSE_CATEGORY_SORT, value = "课程分类排序", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/category/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore CourseCategory courseCategory, @ApiIgnore CourseCategoryView courseCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCategoryView,
                CourseCategoryView.COURSE_CATEGORY_ID,
                CourseCategoryView.APP_ID,
                CourseCategoryView.COURSE_CATEGORY_PARENT_ID,
                CourseCategoryView.COURSE_CATEGORY_PARENT_PATH,
                CourseCategoryView.COURSE_CATEGORY_NAME,
                CourseCategoryView.COURSE_CATEGORY_CODE,
                CourseCategoryView.COURSE_CATEGORY_IMAGE_FILE_ID,
                CourseCategoryView.COURSE_CATEGORY_DESCRIPTION,
                CourseCategoryView.COURSE_CATEGORY_SORT,
                CourseCategoryView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseCategory result = courseCategoryService.update(courseCategory, courseCategory.getCourseCategoryId(), courseCategory.getAppId(), commonView.getSystemRequestUserId(), courseCategory.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseCategoryView.copy(result);

            courseCategoryService.update(courseCategoryView, courseCategoryView.getCourseCategoryId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程分类删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCategoryView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/category/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore CourseCategoryView courseCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCategoryView,
                CourseCategoryView.COURSE_CATEGORY_ID,
                CourseCategoryView.APP_ID,
                CourseCategoryView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseCategory result = courseCategoryService.delete(courseCategoryView.getCourseCategoryId(), courseCategoryView.getAppId(), commonView.getSystemRequestUserId(), courseCategoryView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseCategoryView.copy(result);

            courseCategoryService.update(courseCategoryView, courseCategoryView.getCourseCategoryId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程分类数据同步")
    @RequestMapping(value = "/course/category/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {

        List<CourseCategory> courseCategoryList = courseCategoryService.listByMysql();

        for (CourseCategory courseCategory : courseCategoryList) {
            CourseCategoryView courseCategoryView = new CourseCategoryView();
            courseCategoryView.copy(courseCategory);

            courseCategoryService.saveOrUpdate(courseCategoryView, courseCategoryView.getCourseCategoryId());
        }

        return renderJson(true);
    }

}