package com.nowui.cloud.hospital.course.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.hospital.course.entity.CourseVideo;
import com.nowui.cloud.hospital.course.view.CourseVideoView;
import java.util.List;

/**
 * 课程视频业务接口
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
public interface CourseVideoService extends BaseService<CourseVideo, CourseVideoView> {

    /**
     * 课程视频统计
     *
     * @param appId 应用编号
     * @param courseId 课程编号
     * @param courseVideoTitle 课程视频标题
     * @param courseVideoIntroduce 课程视频简介
     * @return Integer 课程视频统计
     */
    Integer countForAdmin(String appId, String courseId, String courseVideoTitle, String courseVideoIntroduce);

    /**
     * 课程视频列表
     *
     * @param appId 应用编号
     * @param courseId 课程编号
     * @param courseVideoTitle 课程视频标题
     * @param courseVideoIntroduce 课程视频简介
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<CourseVideo> 课程视频列表
     */
    List<CourseVideoView> listForAdmin(String appId, String courseId, String courseVideoTitle, String courseVideoIntroduce, Integer pageIndex, Integer pageSize);

}