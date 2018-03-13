package com.nowui.cloud.code.code.entity;

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
     * 数据表结构
     */
    @TableField(exist = false)
    @NotNull(message = "数据表结构称不能为空")
    @Length(max = 100, message = "数据表结构长度超出限制")
    private String tableSchema;

    /**
     * 数据库名称
     */
    @TableField(exist = false)
    @NotNull(message = "数据库名称不能为空")
    @Length(max = 100, message = "数据库名称长度超出限制")
    private String tableName;

    /**
     * 引擎名称
     */
    @TableField(exist = false)
    private String engine;

    /**
     * 数据库说明
     */
    @TableField(exist = false)
    private String tableComment;

    /**
     * 列名称
     */
    @TableField(exist = false)
    private String columnName;

    /**
     * 列关键字
     */
    @TableField(exist = false)
    private String columnKey;

    /**
     * 列长度
     */
    @TableField(exist = false)
    private String characterMaximumLength;

    /**
     * 列类型
     */
    @TableField(exist = false)
    private String columnType;

    /**
     * 数据类型
     */
    @TableField(exist = false)
    private String dataType;

    /**
     * 列说明
     */
    @TableField(exist = false)
    private String columnComment;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(String characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}