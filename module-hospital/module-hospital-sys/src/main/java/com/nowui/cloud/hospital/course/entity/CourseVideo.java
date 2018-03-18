package com.nowui.cloud.hospital.course.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 课程视频
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Component

@TableName(value = "course_video_info")
public class CourseVideo extends BaseEntity {

    /**
     * 课程视频编号
     */
    @TableId
    @TableField
    private String courseVideoId;

    /**
     * 项目编号
     */
    @TableField
    private String appId;

    /**
     * 课程编号
     */
    @TableField
    private String courseId;

    /**
     * 课程视频标题
     */
    @TableField
    private String courseVideoTitle;

    /**
     * 课程视频简介
     */
    @TableField
    private String courseVideoIntroduce;

    /**
     * 课程视频文件编号
     */
    @TableField
    private String courseVideoFileId;

    /**
     * 课程视频封面截图文件编号
     */
    @TableField
    private String courseVideoCoverFileId;

    /**
     * 课程视频排序
     */
    @TableField
    private Integer courseVideoSort;


    public String getCourseVideoId() {
        return courseVideoId;
    }
    
    public void setCourseVideoId(String courseVideoId) {
        this.courseVideoId = courseVideoId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseVideoTitle() {
        return courseVideoTitle;
    }
    
    public void setCourseVideoTitle(String courseVideoTitle) {
        this.courseVideoTitle = courseVideoTitle;
    }

    public String getCourseVideoIntroduce() {
        return courseVideoIntroduce;
    }
    
    public void setCourseVideoIntroduce(String courseVideoIntroduce) {
        this.courseVideoIntroduce = courseVideoIntroduce;
    }

    public String getCourseVideoFileId() {
        return courseVideoFileId;
    }
    
    public void setCourseVideoFileId(String courseVideoFileId) {
        this.courseVideoFileId = courseVideoFileId;
    }

    public String getCourseVideoCoverFileId() {
        return courseVideoCoverFileId;
    }
    
    public void setCourseVideoCoverFileId(String courseVideoCoverFileId) {
        this.courseVideoCoverFileId = courseVideoCoverFileId;
    }

    public Integer getCourseVideoSort() {
        return courseVideoSort;
    }
    
    public void setCourseVideoSort(Integer courseVideoSort) {
        this.courseVideoSort = courseVideoSort;
    }


}