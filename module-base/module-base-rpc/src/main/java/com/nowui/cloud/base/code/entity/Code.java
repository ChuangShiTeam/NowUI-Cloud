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
}
