package com.nowui.cloud.file.file.view;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.nowui.cloud.annotation.KeyId;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 文件视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "file_info")
public class FileView extends BaseView {

    /**
     * 文件编号
     */
    @KeyId
    @Field
    @NotNull(message = "文件编号不能为空")
    @Length(max = 32, message = "文件编号长度超出限制")
    private String fileId;
    public static final String FILE_ID = "fileId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 类型
     */
    @Field
    @NotNull(message = "类型不能为空")
    @Length(max = 25, message = "类型长度超出限制")
    private String fileType;
    public static final String FILE_TYPE = "fileType";

    /**
     * 名称
     */
    @Field
    @NotNull(message = "名称不能为空")
    @Length(max = 100, message = "名称长度超出限制")
    private String fileName;
    public static final String FILE_NAME = "fileName";

    /**
     * 后缀
     */
    @Field
    @NotNull(message = "后缀不能为空")
    @Length(max = 10, message = "后缀长度超出限制")
    private String fileSuffix;
    public static final String FILE_SUFFIX = "fileSuffix";

    /**
     * 大小
     */
    @Field
    @NotNull(message = "大小不能为空")
    @Length(max = 11, message = "大小长度超出限制")
    private Integer fileSize;
    public static final String FILE_SIZE = "fileSize";

    /**
     * 文件路径
     */
    @Field
    @NotNull(message = "文件路径不能为空")
    @Length(max = 200, message = "文件路径长度超出限制")
    private String filePath;
    public static final String FILE_PATH = "filePath";

    /**
     * 文件路径
     */
    @Field
    @NotNull(message = "文件路径不能为空")
    @Length(max = 200, message = "文件路径长度超出限制")
    private String fileThumbnailPath;
    public static final String FILE_THUMBNAIL_PATH = "fileThumbnailPath";

    /**
     * 文件路径
     */
    @Field
    @NotNull(message = "文件路径不能为空")
    @Length(max = 200, message = "文件路径长度超出限制")
    private String fileOriginalPath;
    public static final String FILE_ORIGINAL_PATH = "fileOriginalPath";

    /**
     * 文件封面图片
     */
    @Field
    @NotNull(message = "文件封面图片不能为空")
    @Length(max = 32, message = "文件封面图片长度超出限制")
    private String fileCoverImage;
    public static final String FILE_COVER_IMAGE = "fileCoverImage";

    /**
     * 文件外部链接
     */
    @Field
    @NotNull(message = "文件外部链接不能为空")
    @Length(max = 200, message = "外部链接长度超出限制")
    private String fileOuterLink;
    public static final String FILE_OUTER_LINK = "fileOuterLink";

    /**
     * 是否外部链接
     */
    @Field
    @NotNull(message = "是否外部链接不能为空")
    private Boolean fileIsOuter;
    public static final String FILE_IS_OUTER = "fileIsOuter";
    
    /**
     * base64数据
     */
    @NotNull(message = "base64数据不能为空")
    private String base64Data;
    public static final String BASE_64_DATA = "base64Data";
    
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
    
    public String getBase64Data() {
        return base64Data;
    }
    
    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

}