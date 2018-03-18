package com.nowui.cloud.hospital.course.controller.system;

import com.nowui.cloud.hospital.course.rpc.CourseRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程系统端控制器
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Api(value = "课程", description = "课程系统端接口管理")
@RestController
public class CourseSystemController implements CourseRpc {

}