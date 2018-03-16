package com.nowui.cloud.hospital.course.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.hospital.course.entity.CourseVideo;
import com.nowui.cloud.hospital.course.view.CourseVideoView;
import com.nowui.cloud.hospital.course.service.CourseVideoService;
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
 * 课程视频管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Api(value = "课程视频", description = "课程视频管理端接口管理")
@RestController
public class CourseVideoAdminController extends BaseController {

    @Autowired
    private CourseVideoService courseVideoService;

    @ApiOperation(value = "课程视频列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseVideoView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_TITLE, value = "课程视频标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_INTRODUCE, value = "课程视频简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/course/video/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore CourseVideoView courseVideoView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseVideoView,
                CourseVideoView.APP_ID,
                CourseVideoView.COURSE_ID,
                CourseVideoView.COURSE_VIDEO_TITLE,
                CourseVideoView.COURSE_VIDEO_INTRODUCE
        );

        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = courseVideoService.countForAdmin(courseVideoView.getAppId(), courseVideoView.getCourseId(), courseVideoView.getCourseVideoTitle(), courseVideoView.getCourseVideoIntroduce());
        List<CourseVideoView> resultList = courseVideoService.listForAdmin(courseVideoView.getAppId(), courseVideoView.getCourseId(), courseVideoView.getCourseVideoTitle(), courseVideoView.getCourseVideoIntroduce(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                CourseVideoView.COURSE_VIDEO_ID,
                CourseVideoView.COURSE_ID,
                CourseVideoView.COURSE_VIDEO_TITLE,
                CourseVideoView.COURSE_VIDEO_INTRODUCE,
                CourseVideoView.COURSE_VIDEO_FILE_ID,
                CourseVideoView.COURSE_VIDEO_COVER_FILE_ID,
                CourseVideoView.COURSE_VIDEO_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "课程视频信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_ID, value = "课程视频编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/video/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore CourseVideoView courseVideoView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseVideoView,
                CourseVideoView.COURSE_VIDEO_ID,
                CourseVideoView.APP_ID
        );

        CourseVideoView result = courseVideoService.find(courseVideoView.getCourseVideoId(), courseVideoView.getAppId());

        validateResponse(
                CourseVideoView.COURSE_VIDEO_ID,
            	CourseVideoView.COURSE_ID,
            	CourseVideoView.COURSE_VIDEO_TITLE,
            	CourseVideoView.COURSE_VIDEO_INTRODUCE,
            	CourseVideoView.COURSE_VIDEO_FILE_ID,
            	CourseVideoView.COURSE_VIDEO_COVER_FILE_ID,
            	CourseVideoView.COURSE_VIDEO_SORT,
                CourseVideoView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "课程视频新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseVideoView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_TITLE, value = "课程视频标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_INTRODUCE, value = "课程视频简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_FILE_ID, value = "课程视频文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_COVER_FILE_ID, value = "课程视频封面截图文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_SORT, value = "课程视频排序", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/video/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore CourseVideo courseVideo, @ApiIgnore CourseVideoView courseVideoView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseVideoView,
                CourseVideoView.APP_ID,
                CourseVideoView.COURSE_ID,
                CourseVideoView.COURSE_VIDEO_TITLE,
                CourseVideoView.COURSE_VIDEO_INTRODUCE,
                CourseVideoView.COURSE_VIDEO_FILE_ID,
                CourseVideoView.COURSE_VIDEO_COVER_FILE_ID,
                CourseVideoView.COURSE_VIDEO_SORT
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String courseVideoId = Util.getRandomUUID();

        CourseVideo result = courseVideoService.save(courseVideo, courseVideoId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            courseVideoView.copy(result);

            courseVideoService.save(courseVideoView);

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程视频修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseVideoView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_TITLE, value = "课程视频标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_INTRODUCE, value = "课程视频简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_FILE_ID, value = "课程视频文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_COVER_FILE_ID, value = "课程视频封面截图文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.COURSE_VIDEO_SORT, value = "课程视频排序", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/video/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore CourseVideo courseVideo, @ApiIgnore CourseVideoView courseVideoView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseVideoView,
                CourseVideoView.COURSE_VIDEO_ID,
                CourseVideoView.APP_ID,
                CourseVideoView.COURSE_ID,
                CourseVideoView.COURSE_VIDEO_TITLE,
                CourseVideoView.COURSE_VIDEO_INTRODUCE,
                CourseVideoView.COURSE_VIDEO_FILE_ID,
                CourseVideoView.COURSE_VIDEO_COVER_FILE_ID,
                CourseVideoView.COURSE_VIDEO_SORT,
                CourseVideoView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseVideo result = courseVideoService.update(courseVideo, courseVideo.getCourseVideoId(), courseVideo.getAppId(), commonView.getSystemRequestUserId(), courseVideo.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseVideoView.copy(result);

            courseVideoService.update(courseVideoView, courseVideoView.getCourseVideoId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程视频删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseVideoView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseVideoView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/video/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore CourseVideoView courseVideoView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseVideoView,
                CourseVideoView.COURSE_VIDEO_ID,
                CourseVideoView.APP_ID,
                CourseVideoView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseVideo result = courseVideoService.delete(courseVideoView.getCourseVideoId(), courseVideoView.getAppId(), commonView.getSystemRequestUserId(), courseVideoView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseVideoView.copy(result);

            courseVideoService.update(courseVideoView, courseVideoView.getCourseVideoId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程视频数据同步")
    @RequestMapping(value = "/course/video/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {

        List<CourseVideo> courseVideoList = courseVideoService.listByMysql();

        for (CourseVideo courseVideo : courseVideoList) {
            CourseVideoView courseVideoView = new CourseVideoView();
            courseVideoView.copy(courseVideo);

            courseVideoService.saveOrUpdate(courseVideoView, courseVideoView.getCourseVideoId());
        }

        return renderJson(true);
    }

}