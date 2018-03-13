package com.nowui.cloud.file.file.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文件
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component
@TableName(value = "file_info")
public class File extends BaseEntity {

    /**
     * 文件编号
     */
    @TableId
    private String fileId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 类型
     */
    @TableField
    private String fileType;

    /**
     * 名称
     */
    @TableField
    private String fileName;

    /**
     * 后缀
     */
    @TableField
    private String fileSuffix;

    /**
     * 大小
     */
    @TableField
    private Integer fileSize;

    /**
     * 文件路径
     */
    @TableField
    private String filePath;

    /**
     * 文件路径
     */
    @TableField
    private String fileThumbnailPath;

    /**
     * 文件路径
     */
    @TableField
    private String fileOriginalPath;

    /**
     * 文件封面图片
     */
    @TableField
    private String fileCoverImage;
    
    /**
     * 外部链接
     */
    @TableField
    private String fileOuterLink;
    
    /**
     * 是否外部链接
     */
    @TableField
    private Boolean fileIsOuter;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileThumbnailPath() {
        return fileThumbnailPath;
    }

    public void setFileThumbnailPath(String fileThumbnailPath) {
        this.fileThumbnailPath = fileThumbnailPath;
    }

    public String getFileOriginalPath() {
        return fileOriginalPath;
    }

    public void setFileOriginalPath(String fileOriginalPath) {
        this.fileOriginalPath = fileOriginalPath;
    }

    public String getFileCoverImage() {
        return fileCoverImage;
    }

    public void setFileCoverImage(String fileCoverImage) {
        this.fileCoverImage = fileCoverImage;
    }

    public String getFileOuterLink() {
        return fileOuterLink;
    }

    public void setFileOuterLink(String fileOuterLink) {
        this.fileOuterLink = fileOuterLink;
    }

    public Boolean getFileIsOuter() {
        return fileIsOuter;
    }

    public void setFileIsOuter(Boolean fileIsOuter) {
        this.fileIsOuter = fileIsOuter;
    }
    
}