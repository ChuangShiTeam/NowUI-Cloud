package com.nowui.cloud.hospital.course.controller.system;

import com.nowui.cloud.hospital.course.rpc.CourseOrderRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程订单编号系统端控制器
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Api(value = "课程订单编号", description = "课程订单编号系统端接口管理")
@RestController
public class CourseOrderSystemController implements CourseOrderRpc {

}