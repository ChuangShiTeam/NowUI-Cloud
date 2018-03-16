package com.nowui.cloud.hospital.course.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 课程分类
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component

@TableName(value = "course_category_info")
public class CourseCategory extends BaseEntity {

    /**
     * 课程分类编号
     */
    @TableId
    @TableField
    private String courseCategoryId;

    /**
     * 项目编号
     */
    @TableField
    private String appId;

    /**
     * 课程父分类编号
     */
    @TableField
    private String courseCategoryParentId;

    /**
     * 课程父分类路径
     */
    @TableField
    private String courseCategoryParentPath;

    /**
     * 课程分类名称
     */
    @TableField
    private String courseCategoryName;

    /**
     * 课程分类编码
     */
    @TableField
    private String courseCategoryCode;

    /**
     * 课程分类图片文件编号
     */
    @TableField
    private String courseCategoryImageFileId;

    /**
     * 课程分类描述
     */
    @TableField
    private String courseCategoryDescription;

    /**
     * 课程分类排序
     */
    @TableField
    private Integer courseCategorySort;


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