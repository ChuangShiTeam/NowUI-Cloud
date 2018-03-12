package com.nowui.cloud.code.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.code.code.entity.Code;
import com.nowui.cloud.code.code.mapper.CodeMapper;
import com.nowui.cloud.code.code.repository.CodeRepository;
import com.nowui.cloud.code.code.service.CodeService;
import com.nowui.cloud.code.code.view.CodeView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 代码生成service实现
 * 
 * @author marcus
 *
 */
@Service
public class CodeServiceImpl extends BaseServiceImpl<CodeMapper, Code, CodeRepository, CodeView> implements CodeService {

    @Override
    public List<Code> tableSchemaList(String tableSchema, String tableName) {
        return mapper.tableSchemaList(tableSchema, tableName);
    }

    @Override
    public List<Code> tableNameList(String tableSchema, String tableName) {
        return mapper.tableNameList(tableSchema, tableName);
    }

}
