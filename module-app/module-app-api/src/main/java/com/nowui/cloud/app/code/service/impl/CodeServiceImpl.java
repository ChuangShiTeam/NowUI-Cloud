package com.nowui.cloud.app.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.app.code.mapper.CodeMapper;
import com.nowui.cloud.app.code.service.CodeService;

/**
 * 代码生成service实现
 * 
 * @author marcus
 *
 */
@Service
public class CodeServiceImpl implements CodeService {
    
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Map<String, Object>> selectTableListByTableSchema(String tableSchema, String tableName) {
        return codeMapper.selectTableListByTableSchema(tableSchema, tableName);
    }

    @Override
    public List<Map<String, Object>> selectTableFieldListByTableName(String tableSchema, String tableName) {
        return codeMapper.selectTableFieldListByTableName(tableSchema, tableName);
    }

}
