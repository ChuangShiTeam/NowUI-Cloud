package com.nowui.cloud.hospital.course.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.hospital.course.entity.Course;
import com.nowui.cloud.hospital.course.view.CourseView;
import com.nowui.cloud.hospital.course.service.CourseService;
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
 * 课程管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Api(value = "课程", description = "课程管理端接口管理")
@RestController
public class CourseAdminController extends BaseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "课程列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AUTHOR_USER_ID, value = "课程作者的userId", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AUTHOR_DOCTOR_ID, value = "课程作者的医生Id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_TITLE, value = "课程标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/course/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore CourseView courseView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseView,
                CourseView.APP_ID,
                CourseView.COURSE_AUTHOR_USER_ID,
                CourseView.COURSE_AUTHOR_DOCTOR_ID,
                CourseView.COURSE_TITLE
        );

        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = courseService.countForAdmin(courseView.getAppId(), courseView.getCourseAuthorUserId(), courseView.getCourseAuthorDoctorId(), courseView.getCourseTitle());
        List<CourseView> resultList = courseService.listForAdmin(courseView.getAppId(), courseView.getCourseAuthorUserId(), courseView.getCourseAuthorDoctorId(), courseView.getCourseTitle(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                CourseView.COURSE_ID,
                CourseView.COURSE_AUTHOR_USER_ID,
                CourseView.COURSE_AUTHOR_DOCTOR_ID,
                CourseView.COURSE_TITLE,
                CourseView.COURSE_INTRODUCE,
                CourseView.COURSE_AMOUNT,
                CourseView.COURSE_COVER_CONTENT,
                CourseView.COURSE_COVER_IMAGE_FILE_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore CourseView courseView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseView,
                CourseView.COURSE_ID,
                CourseView.APP_ID
        );

        CourseView result = courseService.find(courseView.getCourseId(), courseView.getAppId());

        validateResponse(
                CourseView.COURSE_ID,
            	CourseView.COURSE_AUTHOR_USER_ID,
            	CourseView.COURSE_AUTHOR_DOCTOR_ID,
            	CourseView.COURSE_TITLE,
            	CourseView.COURSE_INTRODUCE,
            	CourseView.COURSE_AMOUNT,
            	CourseView.COURSE_COVER_CONTENT,
            	CourseView.COURSE_COVER_IMAGE_FILE_ID,
                CourseView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "课程新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AUTHOR_USER_ID, value = "课程作者的userId", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AUTHOR_DOCTOR_ID, value = "课程作者的医生Id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_TITLE, value = "课程标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_INTRODUCE, value = "课程介绍", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AMOUNT, value = "课程金额", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_COVER_CONTENT, value = "课程栏目封面推荐词", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_COVER_IMAGE_FILE_ID, value = "课程封面图片", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore Course course, @ApiIgnore CourseView courseView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseView,
                CourseView.APP_ID,
                CourseView.COURSE_AUTHOR_USER_ID,
                CourseView.COURSE_AUTHOR_DOCTOR_ID,
                CourseView.COURSE_TITLE,
                CourseView.COURSE_INTRODUCE,
                CourseView.COURSE_AMOUNT,
                CourseView.COURSE_COVER_CONTENT,
                CourseView.COURSE_COVER_IMAGE_FILE_ID
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String courseId = Util.getRandomUUID();

        Course result = courseService.save(course, courseId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            courseView.copy(result);

            courseService.save(courseView);

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AUTHOR_USER_ID, value = "课程作者的userId", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AUTHOR_DOCTOR_ID, value = "课程作者的医生Id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_TITLE, value = "课程标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_INTRODUCE, value = "课程介绍", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_AMOUNT, value = "课程金额", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_COVER_CONTENT, value = "课程栏目封面推荐词", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.COURSE_COVER_IMAGE_FILE_ID, value = "课程封面图片", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore Course course, @ApiIgnore CourseView courseView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseView,
                CourseView.COURSE_ID,
                CourseView.APP_ID,
                CourseView.COURSE_AUTHOR_USER_ID,
                CourseView.COURSE_AUTHOR_DOCTOR_ID,
                CourseView.COURSE_TITLE,
                CourseView.COURSE_INTRODUCE,
                CourseView.COURSE_AMOUNT,
                CourseView.COURSE_COVER_CONTENT,
                CourseView.COURSE_COVER_IMAGE_FILE_ID,
                CourseView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        Course result = courseService.update(course, course.getCourseId(), course.getAppId(), commonView.getSystemRequestUserId(), course.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseView.copy(result);

            courseService.update(courseView, courseView.getCourseId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore CourseView courseView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseView,
                CourseView.COURSE_ID,
                CourseView.APP_ID,
                CourseView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        Course result = courseService.delete(courseView.getCourseId(), courseView.getAppId(), commonView.getSystemRequestUserId(), courseView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseView.copy(result);

            courseService.update(courseView, courseView.getCourseId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程数据同步")
    @RequestMapping(value = "/course/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {

        List<Course> courseList = courseService.listByMysql();

        for (Course course : courseList) {
            CourseView courseView = new CourseView();
            courseView.copy(course);

            courseService.saveOrUpdate(courseView, courseView.getCourseId());
        }

        return renderJson(true);
    }

}