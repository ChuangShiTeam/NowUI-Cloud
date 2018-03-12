package com.nowui.cloud.code.code.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.view.BaseView;

/**
 * 代码视图
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
@Document(collection = "code_info")
public class CodeView extends BaseView {

    /**
     * 数据库名称
     */
    @Field
    @NotNull(message = "数据库名称不能为空")
    @Length(max = 100, message = "数据库名称长度超出限制")
    private String tableSchema;
    public static final String TABLE_SCHEMA = "tableSchema";

    /**
     * 引擎名称
     */
    @Field
    private String engine;
    public static final String ENGINE = "engine";

    /**
     * 表注释
     */
    @Field
    private String tableComment;
    public static final String TABLE_COMMENT = "tableComment";
    
    /**
     * 数据表名称
     */
    @Field
    @NotNull(message = "数据表名称不能为空")
    @Length(max = 100, message = "数据表名称长度超出限制")
    private String tableName;
    public static final String TABLE_NAME = "tableName";

    /**
     * 列名称
     */
    @Field
    private String columnName;
    public static final String COLUMN_NAME = "columnName";

    /**
     * 列关键字
     */
    @Field
    private String columnKey;
    public static final String COLUMN_KEY = "columnKey";

    /**
     * 列长度
     */
    @Field
    private String characterMaximumLength;
    public static final String CHARACTER_MAXIMUM_LENGTH = "characterMaximumLength";

    /**
     * 列类型
     */
    @Field
    private String columnType;
    public static final String COLUMN_TYPE = "columnType";

    /**
     * 数据类型
     */
    @Field
    private String dataType;
    public static final String DATA_TYPE = "dataType";

    /**
     * 列说明
     */
    @Field
    private String columnComment;
    public static final String COLUMN_COMMENT = "columnComment";

    /**
     * 数据列表
     */
    @Field
    private JSONArray columnList;
    public static final String COLUMN_LIST = "columnList";

    /**
     * 模块名称
     */
    @Field
    private String moduleName;
    public static final String MODULE_NAME = "moduleName";

    /**
     * 命名空间
     */
    @Field
    private String packageName;
    public static final String PACKAGE_NAME = "packageName";

    /**
     * 开发者
     */
    @Field
    private String author;
    public static final String AUTHOR = "author";

    /**
     * 是否消息队列
     */
    @Field
    private Boolean isMq;
    public static final String IS_MQ = "isMq";
    
    /**
     * 是否搜索
     */
    @Field
    private Boolean isSearch;
    public static final String IS_SEARCH = "isSearch";
    
    /**
     * 是否列表
     */
    @Field
    private Boolean isList;
    public static final String IS_LIST = "isList";
    
    /**
     * 是否详情
     */
    @Field
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
