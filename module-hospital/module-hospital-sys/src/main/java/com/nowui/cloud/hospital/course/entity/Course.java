package com.nowui.cloud.hospital.course.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * 课程
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component

@TableName(value = "course_info")
public class Course extends BaseEntity {

    /**
     * 课程编号
     */
    @TableId
    @TableField
    private String courseId;

    /**
     * 项目编号
     */
    @TableField
    private String appId;

    /**
     * 课程作者的userId
     */
    @TableField
    private String courseAuthorUserId;

    /**
     * 课程作者的医生Id
     */
    @TableField
    private String courseAuthorDoctorId;

    /**
     * 课程标题
     */
    @TableField
    private String courseTitle;

    /**
     * 课程介绍
     */
    @TableField
    private String courseIntroduce;

    /**
     * 课程金额
     */
    @TableField
    private BigDecimal courseAmount;

    /**
     * 课程栏目封面推荐词
     */
    @TableField
    private String courseCoverContent;

    /**
     * 课程封面图片
     */
    @TableField
    private String courseCoverImageFileId;


    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCourseAuthorUserId() {
        return courseAuthorUserId;
    }
    
    public void setCourseAuthorUserId(String courseAuthorUserId) {
        this.courseAuthorUserId = courseAuthorUserId;
    }

    public String getCourseAuthorDoctorId() {
        return courseAuthorDoctorId;
    }
    
    public void setCourseAuthorDoctorId(String courseAuthorDoctorId) {
        this.courseAuthorDoctorId = courseAuthorDoctorId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseIntroduce() {
        return courseIntroduce;
    }
    
    public void setCourseIntroduce(String courseIntroduce) {
        this.courseIntroduce = courseIntroduce;
    }

    public BigDecimal getCourseAmount() {
        return courseAmount;
    }
    
    public void setCourseAmount(BigDecimal courseAmount) {
        this.courseAmount = courseAmount;
    }

    public String getCourseCoverContent() {
        return courseCoverContent;
    }
    
    public void setCourseCoverContent(String courseCoverContent) {
        this.courseCoverContent = courseCoverContent;
    }

    public String getCourseCoverImageFileId() {
        return courseCoverImageFileId;
    }
    
    public void setCourseCoverImageFileId(String courseCoverImageFileId) {
        this.courseCoverImageFileId = courseCoverImageFileId;
    }


}