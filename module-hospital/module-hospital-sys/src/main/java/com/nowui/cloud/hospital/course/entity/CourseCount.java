package com.nowui.cloud.hospital.course.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 课程统计
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Component

@TableName(value = "course_count")
public class CourseCount extends BaseEntity {

    /**
     * 课程统计信息表
     */
    @TableId
    @TableField
    private String courseCountId;

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
     * 课程统计类型
     */
    @TableField
    private String courseCountType;

    /**
     * 课程统计数量
     */
    @TableField
    private Integer courseCountValue;


    public String getCourseCountId() {
        return courseCountId;
    }
    
    public void setCourseCountId(String courseCountId) {
        this.courseCountId = courseCountId;
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

    public String getCourseCountType() {
        return courseCountType;
    }
    
    public void setCourseCountType(String courseCountType) {
        this.courseCountType = courseCountType;
    }

    public Integer getCourseCountValue() {
        return courseCountValue;
    }
    
    public void setCourseCountValue(Integer courseCountValue) {
        this.courseCountValue = courseCountValue;
    }


}