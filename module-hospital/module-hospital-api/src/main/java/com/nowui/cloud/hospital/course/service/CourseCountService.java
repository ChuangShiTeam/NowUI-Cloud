package com.nowui.cloud.hospital.course.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.hospital.course.entity.CourseCount;
import com.nowui.cloud.hospital.course.view.CourseCountView;
import java.util.List;

/**
 * 课程统计业务接口
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
public interface CourseCountService extends BaseService<CourseCount, CourseCountView> {

    /**
     * 课程统计统计
     *
     * @param appId 应用编号
     * @param courseId 课程编号
     * @param courseCountType 课程统计类型
     * @return Integer 课程统计统计
     */
    Integer countForAdmin(String appId, String courseId, String courseCountType);

    /**
     * 课程统计列表
     *
     * @param appId 应用编号
     * @param courseId 课程编号
     * @param courseCountType 课程统计类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<CourseCount> 课程统计列表
     */
    List<CourseCountView> listForAdmin(String appId, String courseId, String courseCountType, Integer pageIndex, Integer pageSize);

}