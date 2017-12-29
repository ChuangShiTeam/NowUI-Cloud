package com.nowui.cloud.base.code.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 应用
 * 
 * @author marcus
 *
 * 2017年12月25日
 */
@Component
@TableName(value = "code_info")
public class Code extends BaseEntity {
    
    /**
     * 数据库名称
     */
    @TableField(exist = false)
    @NotNull(message = "数据库名称不能为空")
    @Length(max = 100, message = "数据库名称长度超出限制")
    private String tableSchema;
    public static final String TABLE_SCHEMA = "tableSchema";

    /**
     * 引擎名称
     */
    @TableField(exist = false)
    private String engine;
    public static final String ENGINE = "engine";

    /**
     * 数据库说明
     */
    @TableField(exist = false)
    private String tableComment;
    public static final String TABLE_COMMENT = "tableComment";

    /**
     * 列名称
     */
    @TableField(exist = false)
    private String columnName;
    public static final String COLUMN_NAME = "columnName";

    /**
     * 列关键字
     */
    @TableField(exist = false)
    private String columnKey;
    public static final String COLUMN_KEY = "columnKey";

    /**
     * 列长度
     */
    @TableField(exist = false)
    private String characterMaximumLength;
    public static final String CHARACTER_MAXIMUM_LENGTH = "characterMaximumLength";

    /**
     * 列类型
     */
    @TableField(exist = false)
    private String columnType;
    public static final String COLUMN_TYPE = "columnType";

    /**
     * 数据类型
     */
    @TableField(exist = false)
    private String dataType;
    public static final String DATA_TYPE = "dataType";

    /**
     * 列说明
     */
    @TableField(exist = false)
    private String columnComment;
    public static final String COLUMN_COMMENT = "columnComment";

    /**
     * 数据列表
     */
    @TableField(exist = false)
    @NotNull(message = "数据列表不能为空")
    private String columnList;
    public static final String COLUMN_LIST = "columnList";

    /**
     * 模块名称
     */
    @TableField(exist = false)
    private String moduleName;
    public static final String MODULE_NAME = "moduleName";

    /**
     * 命名空间
     */
    @TableField(exist = false)
    private String packageName;
    public static final String PACKAGE_NAME = "packageName";

    /**
     * 开发者
     */
    @TableField(exist = false)
    private String author;
    public static final String AUTHOR = "author";

    public String getTableSchema() {
        return getString(TABLE_SCHEMA);
    }

    public void setTableSchema(String tableSchema) {
        put(TABLE_SCHEMA, tableSchema);
    }

    @Override
    public String getTableName() {
        return getString(TABLE_NAME);
    }

    public void setTableName(String tableName) {
        put(TABLE_NAME, tableName);
    }

    public String getEngine() {
        return getString(ENGINE);
    }

    public void setEngine(String engine) {
        put(ENGINE, engine);
    }

    public String getTableComment() {
        return getString(TABLE_COMMENT);
    }

    public void setTableComment(String tableComment) {
        put(TABLE_COMMENT, tableComment);
    }

    public String getColumnName() {
        return getString(COLUMN_NAME);
    }

    public void setColumnName(String columnName) {
        put(COLUMN_NAME, columnName);
    }

    public String getColumnKey() {
        return getString(COLUMN_KEY);
    }

    public void setColumnKey(String columnKey) {
        put(COLUMN_KEY, columnKey);
    }

    public String getCharacterMaximumLength() {
        return getString(CHARACTER_MAXIMUM_LENGTH);
    }

    public void setCharacterMaximumLength(String characterMaximumLength) {
        put(CHARACTER_MAXIMUM_LENGTH, characterMaximumLength);
    }

    public String getColumnType() {
        return getString(COLUMN_TYPE);
    }

    public void setColumnType(String columnType) {
        put(COLUMN_TYPE, columnType);
    }

    public String getDataType() {
        return getString(DATA_TYPE);
    }

    public void setDataType(String dataType) {
        put(DATA_TYPE, dataType);
    }

    public String getColumnComment() {
        return getString(COLUMN_COMMENT);
    }

    public void setColumnComment(String columnComment) {
        put(COLUMN_COMMENT, columnComment);
    }

    public String getColumnList() {
        return getString(COLUMN_LIST);
    }

    public void setColumnList(String columnList) {
        put(COLUMN_LIST, columnList);
    }

    public String getModuleName() {
        return getString(MODULE_NAME);
    }

    public void setModuleName(String moduleName) {
        put(MODULE_NAME, moduleName);
    }

    public String getPackageName() {
        return getString(PACKAGE_NAME);
    }

    public void setPackageName(String packageName) {
        put(PACKAGE_NAME, packageName);
    }

    public String getAuthor() {
        return getString(AUTHOR);
    }

    public void setAuthor(String author) {
        put(AUTHOR, author);
    }
}
