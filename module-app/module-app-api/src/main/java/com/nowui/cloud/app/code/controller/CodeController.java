package com.nowui.cloud.app.code.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.app.code.service.CodeService;
import com.nowui.cloud.constant.Config;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author marcus
 * @since 2017-12-20
 */
@Api(value = "代码生成", description = "代码生成接口管理")
@RestController
public class CodeController extends BaseController {
    
    @Autowired
    private CodeService codeService;
    
    @Autowired
    private Config config;
    
    @ApiOperation(value = "数据库表列表")
    @RequestMapping(value = "/app/code/admin/table/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> tableList(@RequestBody JSONObject jsonObject) {
        //validateRequest(jsonObject, "tableName");
        
        String tableName = jsonObject.getString("tableName");

        List<Map<String, Object>> resultList = codeService.selectTableListByTableSchema(config.getTableSchema(), tableName);

        validateResponse("table_name", "engine", "table_rows", "create_time", "update_time", "table_comment");
        
        return renderJson(resultList.size(), resultList);
    }
    
    @ApiOperation(value = "数据库表字段列表")
    @RequestMapping(value = "/code/admin/table/field/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> fieldlLst(@RequestBody JSONObject jsonObject) {
       // validateRequest(jsonObject, "tableName");
        
        String tableName = jsonObject.getString("tableName");

        List<Map<String, Object>> resultList = codeService.selectTableFieldListByTableName(config.getTableSchema(), tableName);

        validateResponse("column_name", "column_key", "character_maximum_length", "column_type", "data_type", "column_comment");
        
        return renderJson(resultList);
    }
    
    @ApiOperation(value = "数据库表映射代码生成")
    @RequestMapping(value = "/code/admin/generate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> generate(@RequestBody JSONObject jsonObject) {
        //validateRequest(jsonObject, "tableName", "tableComment", "packageName", "tableFieldList", "author");
        
        String tableName = jsonObject.getString("tableName");
        String tableComment = jsonObject.getString("tableComment");
        String packageName = jsonObject.getString("packageName");
        String author = jsonObject.getString("author");
        JSONArray jsonArray = jsonObject.getJSONArray("tableFieldList");
        
        
        return renderJson(true);
    }
    
}
