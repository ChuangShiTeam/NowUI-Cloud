package com.nowui.cloud.hospital.course.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 课程服务调用
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Component(value = "courseRpc")
@FeignClient(name = "module-hospital")
public interface CourseRpc {

}