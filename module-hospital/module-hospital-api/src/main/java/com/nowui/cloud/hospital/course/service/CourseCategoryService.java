package com.nowui.cloud.hospital.course.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.hospital.course.entity.CourseCategory;
import com.nowui.cloud.hospital.course.view.CourseCategoryView;
import java.util.List;

/**
 * 课程分类业务接口
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
public interface CourseCategoryService extends BaseService<CourseCategory, CourseCategoryView> {

    /**
     * 课程分类统计
     *
     * @param appId 应用编号
     * @param courseCategoryName 课程分类名称
     * @param courseCategoryCode 课程分类编码
     * @param courseCategoryDescription 课程分类描述
     * @return Integer 课程分类统计
     */
    Integer countForAdmin(String appId, String courseCategoryName, String courseCategoryCode, String courseCategoryDescription);

    /**
     * 课程分类列表
     *
     * @param appId 应用编号
     * @param courseCategoryName 课程分类名称
     * @param courseCategoryCode 课程分类编码
     * @param courseCategoryDescription 课程分类描述
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<CourseCategory> 课程分类列表
     */
    List<CourseCategoryView> listForAdmin(String appId, String courseCategoryName, String courseCategoryCode, String courseCategoryDescription, Integer pageIndex, Integer pageSize);

}