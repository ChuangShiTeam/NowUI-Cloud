package com.nowui.cloud.hospital.course.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 课程视图
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Component
@Document(collection = "course_info")
public class CourseView extends BaseView {

    /**
     * 课程编号
     */
    @KeyId
    @Field
    @NotNull(message = "课程编号不能为空")
    @Length(max = 32, message = "课程编号长度超出限制")
    private String courseId;
    public static final String COURSE_ID = "courseId";

    /**
     * 项目编号
     */
    @Field
    @NotNull(message = "项目编号不能为空")
    @Length(max = 32, message = "项目编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 课程作者的userId
     */
    @Field
    @NotNull(message = "课程作者的userId不能为空")
    @Length(max = 32, message = "课程作者的userId长度超出限制")
    private String courseAuthorUserId;
    public static final String COURSE_AUTHOR_USER_ID = "courseAuthorUserId";

    /**
     * 课程作者的医生Id
     */
    @Field
    @NotNull(message = "课程作者的医生Id不能为空")
    @Length(max = 32, message = "课程作者的医生Id长度超出限制")
    private String courseAuthorDoctorId;
    public static final String COURSE_AUTHOR_DOCTOR_ID = "courseAuthorDoctorId";

    /**
     * 课程标题
     */
    @Field
    @NotNull(message = "课程标题不能为空")
    @Length(max = 100, message = "课程标题长度超出限制")
    private String courseTitle;
    public static final String COURSE_TITLE = "courseTitle";

    /**
     * 课程介绍
     */
    @Field
    @NotNull(message = "课程介绍不能为空")
    @Length(max = 200, message = "课程介绍长度超出限制")
    private String courseIntroduce;
    public static final String COURSE_INTRODUCE = "courseIntroduce";

    /**
     * 课程金额
     */
    @Field
    @NotNull(message = "课程金额不能为空")
    @Digits(integer = 10, fraction = 2, message = "课程金额长度超出限制")
    private BigDecimal courseAmount;
    public static final String COURSE_AMOUNT = "courseAmount";

    /**
     * 课程栏目封面推荐词
     */
    @Field
    @NotNull(message = "课程栏目封面推荐词不能为空")
    @Length(max = 200, message = "课程栏目封面推荐词长度超出限制")
    private String courseCoverContent;
    public static final String COURSE_COVER_CONTENT = "courseCoverContent";

    /**
     * 课程封面图片
     */
    @Field
    @NotNull(message = "课程封面图片不能为空")
    @Length(max = 32, message = "课程封面图片长度超出限制")
    private String courseCoverImageFileId;
    public static final String COURSE_COVER_IMAGE_FILE_ID = "courseCoverImageFileId";
    
    /**
     * 课程封面图片路径
     */
    @Field
    @NotNull(message = "课程封面图片路径不能为空")
    private String courseCoverImageFilePath;
    public static final String COURSE_COVER_IMAGE_FILE_PATH = "courseCoverImageFilePath";


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

	public String getCourseCoverImageFilePath() {
		return courseCoverImageFilePath;
	}

	public void setCourseCoverImageFilePath(String courseCoverImageFilePath) {
		this.courseCoverImageFilePath = courseCoverImageFilePath;
	}
    
    
    

}