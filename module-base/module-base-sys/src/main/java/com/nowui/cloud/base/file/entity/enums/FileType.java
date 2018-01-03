package com.nowui.cloud.base.file.entity.enums;

public enum FileType {
    IMAGE("IMAGE", "图片"),
    DOCUMENT("DOCUMENT", "文档"),
    VIDEO("VIDEO", "视频"),
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
