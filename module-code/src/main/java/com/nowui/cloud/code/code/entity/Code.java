package com.nowui.cloud.code.code.entity;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 代码
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
    private String tableSchema;
    public static final String TABLE_SCHEMA = "tableSchema";

    /**
     * 引擎名称
     */
    @TableField(exist = false)
    private String engine;
    public static final String ENGINE = "engine";

    /**
     * 表注释
     */
    @TableField(exist = false)
    private String tableComment;
    public static final String TABLE_COMMENT = "tableComment";
    
    /**
     * 数据表名称
     */
    @TableField(exist = false)
    private String tableName;
    public static final String TABLE_NAME = "tableName";

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
    private JSONArray columnList;
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

    /**
     * 是否消息队列
     */
    @TableField(exist = false)
    private Boolean isMq;
    public static final String IS_MQ = "isMq";
    
    /**
     * 是否搜索
     */
    @TableField(exist = false)
    private Boolean isSearch;
    public static final String IS_SEARCH = "isSearch";
    
    /**
     * 是否列表
     */
    @TableField(exist = false)
    private Boolean isList;
    public static final String IS_LIST = "isList";
    
    /**
     * 是否详情
     */
    @TableField(exist = false)
    private Boolean isDetail;
    public static final String IS_DETAIL = "isDetail";
    
    public String getTableSchema() {
        return tableSchema;
    }
    
    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
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
    
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
    
    public JSONArray getColumnList() {
        return columnList;
    }
    
    public void setColumnList(JSONArray columnList) {
        this.columnList = columnList;
    }
    
    public String getModuleName() {
        return moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Boolean getIsMq() {
        return isMq;
    }
    
    public void setIsMq(Boolean isMq) {
        this.isMq = isMq;
    }

    public Boolean getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(Boolean isSearch) {
        this.isSearch = isSearch;
    }

    public Boolean getIsList() {
        return isList;
    }

    public void setIsList(Boolean isList) {
        this.isList = isList;
    }

    public Boolean getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(Boolean isDetail) {
        this.isDetail = isDetail;
    }

}
