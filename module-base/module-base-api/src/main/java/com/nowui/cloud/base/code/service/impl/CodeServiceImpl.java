package com.nowui.cloud.base.code.service.impl;

import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.code.entity.Code;
import com.nowui.cloud.service.impl.BaseServiceImpl;

import com.nowui.cloud.base.code.mapper.CodeMapper;
import com.nowui.cloud.base.code.service.CodeService;
import org.springframework.stereotype.Service;

/**
 * 代码生成service实现
 * 
 * @author marcus
 *
 */
@Service
public class CodeServiceImpl extends BaseServiceImpl<CodeMapper, Code> implements CodeService {

    @Override
    public List<Code> tableSchemaList(String tableSchema, String tableName) {
        return mapper.tableSchemaList(tableSchema, tableName);
    }

    @Override
    public List<Code> tableNameList(String tableSchema, String tableName) {
        return mapper.tableNameList(tableSchema, tableName);
    }

}
