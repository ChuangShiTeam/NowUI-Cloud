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
 * 课程分类视图
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component
@Document(collection = "course_category_info")
public class CourseCategoryView extends BaseView {

    /**
     * 课程分类编号
     */
    @KeyId
    @Field
    @NotNull(message = "课程分类编号不能为空")
    @Length(max = 32, message = "课程分类编号长度超出限制")
    private String courseCategoryId;
    public static final String COURSE_CATEGORY_ID = "courseCategoryId";

    /**
     * 项目编号
     */
    @Field
    @NotNull(message = "项目编号不能为空")
    @Length(max = 32, message = "项目编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 课程父分类编号
     */
    @Field
    @NotNull(message = "课程父分类编号不能为空")
    @Length(max = 32, message = "课程父分类编号长度超出限制")
    private String courseCategoryParentId;
    public static final String COURSE_CATEGORY_PARENT_ID = "courseCategoryParentId";

    /**
     * 课程父分类路径
     */
    @Field
    @NotNull(message = "课程父分类路径不能为空")
    @Length(max = 32, message = "课程父分类路径长度超出限制")
    private String courseCategoryParentPath;
    public static final String COURSE_CATEGORY_PARENT_PATH = "courseCategoryParentPath";

    /**
     * 课程分类名称
     */
    @Field
    @NotNull(message = "课程分类名称不能为空")
    @Length(max = 25, message = "课程分类名称长度超出限制")
    private String courseCategoryName;
    public static final String COURSE_CATEGORY_NAME = "courseCategoryName";

    /**
     * 课程分类编码
     */
    @Field
    @NotNull(message = "课程分类编码不能为空")
    @Length(max = 32, message = "课程分类编码长度超出限制")
    private String courseCategoryCode;
    public static final String COURSE_CATEGORY_CODE = "courseCategoryCode";

    /**
     * 课程分类图片文件编号
     */
    @Field
    @NotNull(message = "课程分类图片文件编号不能为空")
    @Length(max = 32, message = "课程分类图片文件编号长度超出限制")
    private String courseCategoryImageFileId;
    public static final String COURSE_CATEGORY_IMAGE_FILE_ID = "courseCategoryImageFileId";

    /**
     * 课程分类描述
     */
    @Field
    @NotNull(message = "课程分类描述不能为空")
    @Length(max = 100, message = "课程分类描述长度超出限制")
    private String courseCategoryDescription;
    public static final String COURSE_CATEGORY_DESCRIPTION = "courseCategoryDescription";

    /**
     * 课程分类排序
     */
    @Field
    @NotNull(message = "课程分类排序不能为空")
    @Digits(integer = 5, fraction = 0, message = "课程分类排序长度超出限制")
    private Integer courseCategorySort;
    public static final String COURSE_CATEGORY_SORT = "courseCategorySort";


    public String getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getCourseCategoryParentId() {
        return courseCategoryParentId;
    }

    public void setCourseCategoryParentId(String courseCategoryParentId) {
        this.courseCategoryParentId = courseCategoryParentId;
    }
    
    public String getCourseCategoryParentPath() {
        return courseCategoryParentPath;
    }

    public void setCourseCategoryParentPath(String courseCategoryParentPath) {
        this.courseCategoryParentPath = courseCategoryParentPath;
    }
    
    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }
    
    public String getCourseCategoryCode() {
        return courseCategoryCode;
    }

    public void setCourseCategoryCode(String courseCategoryCode) {
        this.courseCategoryCode = courseCategoryCode;
    }
    
    public String getCourseCategoryImageFileId() {
        return courseCategoryImageFileId;
    }

    public void setCourseCategoryImageFileId(String courseCategoryImageFileId) {
        this.courseCategoryImageFileId = courseCategoryImageFileId;
    }
    
    public String getCourseCategoryDescription() {
        return courseCategoryDescription;
    }

    public void setCourseCategoryDescription(String courseCategoryDescription) {
        this.courseCategoryDescription = courseCategoryDescription;
    }
    
    public Integer getCourseCategorySort() {
        return courseCategorySort;
    }

    public void setCourseCategorySort(Integer courseCategorySort) {
        this.courseCategorySort = courseCategorySort;
    }
    

}