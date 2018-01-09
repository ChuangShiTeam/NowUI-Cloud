package com.nowui.cloud.base.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nowui.cloud.base.code.entity.Code;
import com.nowui.cloud.mapper.BaseMapper;

/**
 * 代码生成Mapper
 * 
 * @author marcus
 *
 */
public interface CodeMapper extends BaseMapper<Code> {

    /**
     * 数据库列表
     *
     * @param tableSchema 数据库合集
     * @param tableName 数据库名称
     * @return List<Code> 数据库列表
     */
    List<Code> tableSchemaList(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    /**
     * 数据表字段列表
     *
     * @param tableSchema 数据库合集
     * @param tableName 数据库名称
     * @return List<Code> 数据表字段列表
     */
    List<Code> tableNameList(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

}
