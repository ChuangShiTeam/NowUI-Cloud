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
 * 课程视频视图
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component
@Document(collection = "course_video_info")
public class CourseVideoView extends BaseView {

    /**
     * 课程视频编号
     */
    @KeyId
    @Field
    @NotNull(message = "课程视频编号不能为空")
    @Length(max = 32, message = "课程视频编号长度超出限制")
    private String courseVideoId;
    public static final String COURSE_VIDEO_ID = "courseVideoId";

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
     * 课程视频标题
     */
    @Field
    @NotNull(message = "课程视频标题不能为空")
    @Length(max = 100, message = "课程视频标题长度超出限制")
    private String courseVideoTitle;
    public static final String COURSE_VIDEO_TITLE = "courseVideoTitle";

    /**
     * 课程视频简介
     */
    @Field
    @NotNull(message = "课程视频简介不能为空")
    @Length(max = 200, message = "课程视频简介长度超出限制")
    private String courseVideoIntroduce;
    public static final String COURSE_VIDEO_INTRODUCE = "courseVideoIntroduce";

    /**
     * 课程视频文件编号
     */
    @Field
    @NotNull(message = "课程视频文件编号不能为空")
    @Length(max = 32, message = "课程视频文件编号长度超出限制")
    private String courseVideoFileId;
    public static final String COURSE_VIDEO_FILE_ID = "courseVideoFileId";

    /**
     * 课程视频封面截图文件编号
     */
    @Field
    @NotNull(message = "课程视频封面截图文件编号不能为空")
    @Length(max = 32, message = "课程视频封面截图文件编号长度超出限制")
    private String courseVideoCoverFileId;
    public static final String COURSE_VIDEO_COVER_FILE_ID = "courseVideoCoverFileId";

    /**
     * 课程视频排序
     */
    @Field
    @NotNull(message = "课程视频排序不能为空")
    @Digits(integer = 5, fraction = 0, message = "课程视频排序长度超出限制")
    private Integer courseVideoSort;
    public static final String COURSE_VIDEO_SORT = "courseVideoSort";


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