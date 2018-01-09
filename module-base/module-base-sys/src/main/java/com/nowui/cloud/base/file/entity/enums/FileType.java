package com.nowui.cloud.base.file.entity.enums;

/**
 * 文件类型枚举
 * 
 * @author marcus
 *
 * 2018年1月7日
 */
public enum FileType {
    /**
     * 图片
     */
    IMAGE("IMAGE", "图片"),
    /**
     * 文档
     */
    DOCUMENT("DOCUMENT", "文档"),
    /**
     * 视频
     */
    VIDEO("VIDEO", "视频"),
    /**
     * 其他
     */
    OTHER("OTHER", "其他");

    private String key;
    private String value;

    private FileType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
