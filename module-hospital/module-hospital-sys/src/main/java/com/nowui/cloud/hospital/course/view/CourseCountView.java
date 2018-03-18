package com.nowui.cloud.hospital.course.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 课程统计视图
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Component
@Document(collection = "course_count")
public class CourseCountView extends BaseView {

    /**
     * 课程统计信息表
     */
    @KeyId
    @Field
    @NotNull(message = "课程统计信息表不能为空")
    @Length(max = 32, message = "课程统计信息表长度超出限制")
    private String courseCountId;
    public static final String COURSE_COUNT_ID = "courseCountId";

    /**
     * 项目编号
     */
    @Field
    @NotNull(message = "项目编号不能为空")
    @Length(max = 32, message = "项目编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 课程编号
     */
    @Field
    @NotNull(message = "课程编号不能为空")
    @Length(max = 32, message = "课程编号长度超出限制")
    private String courseId;
    public static final String COURSE_ID = "courseId";

    /**
     * 课程统计类型
     */
    @Field
    @NotNull(message = "课程统计类型不能为空")
    @Length(max = 25, message = "课程统计类型长度超出限制")
    private String courseCountType;
    public static final String COURSE_COUNT_TYPE = "courseCountType";

    /**
     * 课程统计数量
     */
    @Field
    @NotNull(message = "课程统计数量不能为空")
    @Digits(integer = 11, fraction = 0, message = "课程统计数量长度超出限制")
    private Integer courseCountValue;
    public static final String COURSE_COUNT_VALUE = "courseCountValue";


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