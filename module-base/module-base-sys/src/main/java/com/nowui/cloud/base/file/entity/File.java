package com.nowui.cloud.base.file.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "文件编号不能为空")
    @Length(max = 32, message = "文件编号长度超出限制")
    private String fileId;
    public static final String FILE_ID = "fileId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 类型
     */
    @TableField
    @NotNull(message = "类型不能为空")
    @Length(max = 25, message = "类型长度超出限制")
    private String fileType;
    public static final String FILE_TYPE = "fileType";

    /**
     * 名称
     */
    @TableField
    @NotNull(message = "名称不能为空")
    @Length(max = 100, message = "名称长度超出限制")
    private String fileName;
    public static final String FILE_NAME = "fileName";

    /**
     * 后缀
     */
    @TableField
    @NotNull(message = "后缀不能为空")
    @Length(max = 10, message = "后缀长度超出限制")
    private String fileSuffix;
    public static final String FILE_SUFFIX = "fileSuffix";

    /**
     * 大小
     */
    @TableField
    @NotNull(message = "大小不能为空")
    @Length(max = 11, message = "大小长度超出限制")
    private Integer fileSize;
    public static final String FILE_SIZE = "fileSize";

    /**
     * 文件路径
     */
    @TableField
    @NotNull(message = "文件路径不能为空")
    @Length(max = 200, message = "文件路径长度超出限制")
    private String filePath;
    public static final String FILE_PATH = "filePath";

    /**
     * 文件路径
     */
    @TableField
    @NotNull(message = "文件路径不能为空")
    @Length(max = 200, message = "文件路径长度超出限制")
    private String fileThumbnailPath;
    public static final String FILE_THUMBNAIL_PATH = "fileThumbnailPath";

    /**
     * 文件路径
     */
    @TableField
    @NotNull(message = "文件路径不能为空")
    @Length(max = 200, message = "文件路径长度超出限制")
    private String fileOriginalPath;
    public static final String FILE_ORIGINAL_PATH = "fileOriginalPath";

    /**
     * 文件封面图片
     */
    @TableField
    @NotNull(message = "文件封面图片不能为空")
    @Length(max = 32, message = "文件封面图片长度超出限制")
    private String fileCoverImage;
    public static final String FILE_COVER_IMAGE = "fileCoverImage";

    /**
     * 是否外部链接
     */
    @TableField
    @NotNull(message = "是否外部链接不能为空")
    @Length(max = 1, message = "是否外部链接长度超出限制")
    private Boolean fileIsExternal;
    public static final String FILE_IS_EXTERNAL = "fileIsExternal";


    public String getFileId() {
        return getString(FILE_ID);
    }

    public void setFileId(String fileId) {
        put(FILE_ID, fileId);
    }
    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    public String getFileType() {
        return getString(FILE_TYPE);
    }

    public void setFileType(String fileType) {
        put(FILE_TYPE, fileType);
    }
    public String getFileName() {
        return getString(FILE_NAME);
    }

    public void setFileName(String fileName) {
        put(FILE_NAME, fileName);
    }
    
    public String getFileSuffix() {
        return getString(FILE_SUFFIX);
    }

    public void setFileSuffix(String fileSuffix) {
        put(FILE_SUFFIX, fileSuffix);
    }
    
    public Integer getFileSize() {
        return getInteger(FILE_SIZE);
    }

    public void setFileSize(Integer fileSize) {
        put(FILE_SIZE, fileSize);
    }
    
    public String getFilePath() {
        return getString(FILE_PATH);
    }

    public void setFilePath(String filePath) {
        put(FILE_PATH, filePath);
    }
    
    public String getFileThumbnailPath() {
        return getString(FILE_THUMBNAIL_PATH);
    }

    public void setFileThumbnailPath(String fileThumbnailPath) {
        put(FILE_THUMBNAIL_PATH, fileThumbnailPath);
    }
    
    public String getFileOriginalPath() {
        return getString(FILE_ORIGINAL_PATH);
    }

    public void setFileOriginalPath(String fileOriginalPath) {
        put(FILE_ORIGINAL_PATH, fileOriginalPath);
    }
    
    public String getFileCoverImage() {
        return getString(FILE_COVER_IMAGE);
    }

    public void setFileCoverImage(String fileCoverImage) {
        put(FILE_COVER_IMAGE, fileCoverImage);
    }
    
    public Boolean getFileIsExternal() {
        return getBoolean(FILE_IS_EXTERNAL);
    }

    public void setFileIsExternal(Boolean fileIsExternal) {
        put(FILE_IS_EXTERNAL, fileIsExternal);
    }

}