package com.nowui.cloud.base.code.service;

import java.util.List;
import java.util.Map;

/**
 * 代码生成service接口
 * 
 * @author marcus
 *
 */

public interface CodeService {
    
    /**
     * 查询数据库表列表
     * @param table_schema
     * @param table_name
     * @return
     */
    public List<Map<String, Object>> selectTableListByTableSchema(String table_schema, String table_name);
    
    /**
     * 查询数据库表字段列表
     * @param table_schema
     * @param table_name
     * @return
     */
    public List<Map<String, Object>> selectTableFieldListByTableName(String table_schema, String table_name);


}
