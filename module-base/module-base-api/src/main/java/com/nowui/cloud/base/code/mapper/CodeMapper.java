package com.nowui.cloud.base.code.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 代码生成Mapper
 * 
 * @author marcus
 *
 */
public interface CodeMapper {
    
    public List<Map<String, Object>> selectTableListByTableSchema(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
    
    public List<Map<String, Object>> selectTableFieldListByTableName(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

}
