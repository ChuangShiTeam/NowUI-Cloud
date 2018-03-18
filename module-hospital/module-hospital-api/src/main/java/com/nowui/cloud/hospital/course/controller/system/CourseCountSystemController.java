package com.nowui.cloud.hospital.course.controller.system;

import com.nowui.cloud.hospital.course.rpc.CourseCountRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程统计系统端控制器
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Api(value = "课程统计", description = "课程统计系统端接口管理")
@RestController
public class CourseCountSystemController implements CourseCountRpc {

}