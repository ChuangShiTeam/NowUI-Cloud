package com.nowui.cloud.base.code.mapper;

import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.code.entity.Code;
import com.nowui.cloud.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 代码生成Mapper
 * 
 * @author marcus
 *
 */
public interface CodeMapper extends BaseMapper<Code> {
    
    List<Code> tableSchemaList(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
    
    List<Code> tableNameList(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

}
