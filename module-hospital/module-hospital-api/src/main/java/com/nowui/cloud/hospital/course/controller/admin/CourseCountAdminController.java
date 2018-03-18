package com.nowui.cloud.hospital.course.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.hospital.course.entity.CourseCount;
import com.nowui.cloud.hospital.course.view.CourseCountView;
import com.nowui.cloud.hospital.course.service.CourseCountService;
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
 * 课程统计管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Api(value = "课程统计", description = "课程统计管理端接口管理")
@RestController
public class CourseCountAdminController extends BaseController {

    @Autowired
    private CourseCountService courseCountService;

    @ApiOperation(value = "课程统计列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCountView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_COUNT_TYPE, value = "课程统计类型", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/course/count/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore CourseCountView courseCountView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCountView,
                CourseCountView.APP_ID,
                CourseCountView.COURSE_ID,
                CourseCountView.COURSE_COUNT_TYPE
        );

        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = courseCountService.countForAdmin(courseCountView.getAppId(), courseCountView.getCourseId(), courseCountView.getCourseCountType());
        List<CourseCountView> resultList = courseCountService.listForAdmin(courseCountView.getAppId(), courseCountView.getCourseId(), courseCountView.getCourseCountType(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                CourseCountView.COURSE_COUNT_ID,
                CourseCountView.COURSE_ID,
                CourseCountView.COURSE_COUNT_TYPE,
                CourseCountView.COURSE_COUNT_VALUE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "课程统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCountView.COURSE_COUNT_ID, value = "课程统计信息表", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/count/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore CourseCountView courseCountView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCountView,
                CourseCountView.COURSE_COUNT_ID,
                CourseCountView.APP_ID
        );

        CourseCountView result = courseCountService.find(courseCountView.getCourseCountId(), courseCountView.getAppId());

        validateResponse(
                CourseCountView.COURSE_COUNT_ID,
            	CourseCountView.COURSE_ID,
            	CourseCountView.COURSE_COUNT_TYPE,
            	CourseCountView.COURSE_COUNT_VALUE,
                CourseCountView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "课程统计新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCountView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_COUNT_TYPE, value = "课程统计类型", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_COUNT_VALUE, value = "课程统计数量", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/count/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore CourseCount courseCount, @ApiIgnore CourseCountView courseCountView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCountView,
                CourseCountView.APP_ID,
                CourseCountView.COURSE_ID,
                CourseCountView.COURSE_COUNT_TYPE,
                CourseCountView.COURSE_COUNT_VALUE
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String courseCountId = Util.getRandomUUID();

        CourseCount result = courseCountService.save(courseCount, courseCountId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            courseCountView.copy(result);

            courseCountService.save(courseCountView);

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程统计修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCountView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_COUNT_TYPE, value = "课程统计类型", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.COURSE_COUNT_VALUE, value = "课程统计数量", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/count/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore CourseCount courseCount, @ApiIgnore CourseCountView courseCountView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCountView,
                CourseCountView.COURSE_COUNT_ID,
                CourseCountView.APP_ID,
                CourseCountView.COURSE_ID,
                CourseCountView.COURSE_COUNT_TYPE,
                CourseCountView.COURSE_COUNT_VALUE,
                CourseCountView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseCount result = courseCountService.update(courseCount, courseCount.getCourseCountId(), courseCount.getAppId(), commonView.getSystemRequestUserId(), courseCount.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseCountView.copy(result);

            courseCountService.update(courseCountView, courseCountView.getCourseCountId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程统计删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseCountView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseCountView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/count/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore CourseCountView courseCountView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseCountView,
                CourseCountView.COURSE_COUNT_ID,
                CourseCountView.APP_ID,
                CourseCountView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseCount result = courseCountService.delete(courseCountView.getCourseCountId(), courseCountView.getAppId(), commonView.getSystemRequestUserId(), courseCountView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseCountView.copy(result);

            courseCountService.update(courseCountView, courseCountView.getCourseCountId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程统计数据同步")
    @RequestMapping(value = "/course/count/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {

        List<CourseCount> courseCountList = courseCountService.listByMysql();

        for (CourseCount courseCount : courseCountList) {
            CourseCountView courseCountView = new CourseCountView();
            courseCountView.copy(courseCount);

            courseCountService.saveOrUpdate(courseCountView, courseCountView.getCourseCountId());
        }

        return renderJson(true);
    }

}