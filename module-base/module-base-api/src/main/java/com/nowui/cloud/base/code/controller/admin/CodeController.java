package com.nowui.cloud.base.code.controller.admin;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.code.entity.Code;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.code.service.CodeService;
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

    @ApiOperation(value = "数据库表列表")
    @RequestMapping(value = "/code/admin/table/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> tableList(@RequestBody Code body) {
        validateRequest(body, Code.TABLE_SCHEMA, Code.TABLE_NAME);

        List<Code> resultList = codeService.tableSchemaList(body.getTableSchema(), body.getTableName());

        validateResponse(Code.TABLE_SCHEMA, Code.TABLE_NAME, Code.ENGINE, Code.TABLE_COMMENT, Code.SYSTEM_CREATE_TIME);

        return renderJson(resultList.size(), resultList);
    }

    @ApiOperation(value = "数据库表字段列表")
    @RequestMapping(value = "/code/admin/table/field/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> fieldlLst(@RequestBody Code body) {
        validateRequest(body, Code.TABLE_SCHEMA, Code.TABLE_NAME);

        List<Code> resultList = codeService.tableNameList(body.getTableSchema(), body.getTableName());

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


    @ApiOperation(value = "数据库表字段列表")
    @RequestMapping(value = "/code/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        try {
            ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("template");
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template template = gt.getTemplate("/entity.txt");

            StringWriter writer = new StringWriter();
            template.renderTo(writer);

            OutputStreamWriter outWriter = new OutputStreamWriter(
                    new FileOutputStream("/Users/zhongyongqiang/Documents/Publish/aaaaaa.txt", false), "UTF-8");
            System.out.println(writer.toString());

            Writer out = new BufferedWriter(outWriter);
            out.write(writer.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Hello World 123";
    }

}
