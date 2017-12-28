package com.nowui.cloud.base.code.controller.admin;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.code.entity.Code;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.util.FileUtil;
import com.nowui.cloud.util.Util;
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

        validateResponse(Code.COLUMN_NAME, Code.COLUMN_KEY, Code.CHARACTER_MAXIMUM_LENGTH, Code.COLUMN_TYPE, Code.DATA_TYPE, Code.COLUMN_COMMENT);

        return renderJson(resultList);
    }

    @ApiOperation(value = "数据库表映射代码生成")
    @RequestMapping(value = "/code/admin/generate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> generate(@RequestBody Code body) {
        validateRequest(body, Code.TABLE_NAME, Code.AUTHOR, Code.COLUMN_LIST);

        try {
            String path = CodeController.class.getResource("/").toURI().getPath();
            path = new File(path).getParentFile().getCanonicalPath() + "/" + Constant.PUBLISH;
            String packagePath = path + "/" + body.getPackageName();
            String entityPath = packagePath + "/entity";

            FileUtil.createPath(path);
            FileUtil.createPath(packagePath);
            FileUtil.createPath(entityPath);

            List<Code> codeList = JSONArray.parseArray(body.getColumnList(), Code.class);

            List<JSONObject> columnList = new ArrayList<JSONObject>();
            List<JSONObject> searchColumnList = new ArrayList<JSONObject>();
            List<JSONObject> listColumnList = new ArrayList<JSONObject>();
            List<JSONObject> detailColumnList = new ArrayList<JSONObject>();

            String upperTableName = body.getTableName().toUpperCase();
            String lowerEntityName = body.getTableName().replace("_info", "").replace("_map", "").replace("_count", "").toLowerCase();
            String upperEntityName = lowerEntityName.toUpperCase();
            String urlEntityName = lowerEntityName.replaceAll("_", "/");
            String firstLowerEntityName = lowerEntityName.substring(0, 1).toLowerCase() + lowerEntityName.substring(1);
            String firstUpperEntityName = lowerEntityName.substring(0, 1).toUpperCase() + lowerEntityName.substring(1);
            String firstLowerWithoutUnderlineEntityName = removeUnderline(firstLowerEntityName);
            String firstUpperWithoutUnderlineEntityName = removeUnderline(firstUpperEntityName);
            String tableId = "";

            for (Code code : codeList) {
                String columnName = code.getColumnName();
                String dataType = code.getDataType();

                if (Util.isNullOrEmpty(code.getCharacterMaximumLength())) {
                    String length = code.getColumnType().replace(dataType, "").replace("(", "").replace(")", "");

                    if ("".equals(length) || length.contains(",")) {
                        length = "0";
                    }

                    code.setCharacterMaximumLength(length);
                } else {
                    if ("longtext".equals(dataType)) {
                        code.setCharacterMaximumLength("0");
                    }
                }

                if (code.getColumnKey().equals("PRI")) {
                    tableId = columnName;
                }

                code.put("firstUpperColumnName", columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
                code.put("upperWithUnderlineColumnName", insertUnderline(columnName));
                code.setDataType(dataType.toUpperCase());
                code.put("upperColumnName", columnName.toUpperCase());

                columnList.add(code);

                if (code.getBoolean("isSearch")) {
                    searchColumnList.add(code);
                }

                if (code.getBoolean("isList")) {
                    listColumnList.add(code);
                }

                if (code.getBoolean("isDetail")) {
                    detailColumnList.add(code);
                }
            }

            String firstUpperTableId = "";
            if (!Util.isNullOrEmpty(tableId)) {
                firstUpperTableId = tableId.substring(0, 1).toUpperCase() + tableId.substring(1);
            }
            String upperTableId = tableId.toUpperCase();

            Map<String, Object> templateMap = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
            templateMap.put("packageName", body.getPackageName());
            templateMap.put("tableName", body.getTableName());
            templateMap.put("author", body.getAuthor());
            templateMap.put("upperTableName", upperTableName);
            templateMap.put("tableId", tableId);
            templateMap.put("firstUpperTableId", firstUpperTableId);
            templateMap.put("upperTableId", upperTableId);
            templateMap.put("lowerEntityName", lowerEntityName);
            templateMap.put("urlEntityName", urlEntityName);
            templateMap.put("upperEntityName", upperEntityName);
            templateMap.put("firstLowerEntityName", firstLowerEntityName);
            templateMap.put("firstUpperEntityName", firstUpperEntityName);
            templateMap.put("firstLowerWithoutUnderlineEntityName", firstLowerWithoutUnderlineEntityName);
            templateMap.put("firstUpperWithoutUnderlineEntityName", firstUpperWithoutUnderlineEntityName);
            templateMap.put("columnList", columnList);
            templateMap.put("searchColumnList", searchColumnList);
            templateMap.put("listColumnList", listColumnList);
            templateMap.put("detailColumnList", detailColumnList);

            write(templateMap, "entity.txt", entityPath + "/" + firstUpperWithoutUnderlineEntityName + ".java");

        } catch (URISyntaxException e) {
            e.printStackTrace();

            throw new RuntimeException("代码生成发生错误");
        } catch (IOException e) {
            e.printStackTrace();

            throw new RuntimeException("代码生成发生错误");
        }

        return renderJson(true);
    }

    private String insertUnderline(String name) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < name.length(); i++) {
            char chr = name.charAt(i);

            if (Character.isUpperCase(chr)) {
                sb.append("_");
                sb.append(String.valueOf(chr));
            } else if (Character.isLowerCase(chr)) {
                sb.append(String.valueOf(chr).toUpperCase());
            }
        }

        System.out.println(sb.toString());

        return sb.toString();
    }

    private String removeUnderline(String name) {
        if (name.contains("_")) {
            int index = name.indexOf("_");
            name = name.substring(0, index) + name.substring(index + 1, index + 2).toUpperCase() + name.substring(index + 2);

            if (name.contains("_")) {
                name = removeUnderline(name);

            }
        }

        return name;
    }

    private void write(Map<String, Object> templateMap, String templateName, String filePath) throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("template");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template template = gt.getTemplate(templateName);

        template.binding(templateMap);

        StringWriter writer = new StringWriter();
        template.renderTo(writer);

        OutputStreamWriter outWriter = new OutputStreamWriter(
                new FileOutputStream(filePath, false), "UTF-8");

        Writer out = new BufferedWriter(outWriter);
        out.write(writer.toString());
        out.flush();
        out.close();
    }

}
