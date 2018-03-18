package com.nowui.cloud.hospital.course.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.hospital.course.entity.Course;
import com.nowui.cloud.hospital.course.view.CourseView;
import java.util.List;

/**
 * 课程业务接口
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
public interface CourseService extends BaseService<Course, CourseView> {

    /**
     * 课程统计
     *
     * @param appId 应用编号
     * @param courseAuthorUserId 课程作者的userId
     * @param courseAuthorDoctorId 课程作者的医生Id
     * @param courseTitle 课程标题
     * @return Integer 课程统计
     */
    Integer countForAdmin(String appId, String courseAuthorUserId, String courseAuthorDoctorId, String courseTitle);

    /**
     * 课程列表
     *
     * @param appId 应用编号
     * @param courseAuthorUserId 课程作者的userId
     * @param courseAuthorDoctorId 课程作者的医生Id
     * @param courseTitle 课程标题
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Course> 课程列表
     */
    List<CourseView> listForAdmin(String appId, String courseAuthorUserId, String courseAuthorDoctorId, String courseTitle, Integer pageIndex, Integer pageSize);

}