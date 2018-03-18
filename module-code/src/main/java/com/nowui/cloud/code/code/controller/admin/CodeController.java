package com.nowui.cloud.code.code.controller.admin;

import java.io.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.code.code.entity.Code;
import com.nowui.cloud.code.code.view.CodeView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.util.FileUtil;
import com.nowui.cloud.util.Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.code.code.service.CodeService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author marcus
 * @since 2017-12-20
 */
@Api(value = "代码生成", description = "代码生成接口管理")
@RestController
public class CodeController extends BaseController {

    @Autowired
    private CodeService codeService;

    @ApiOperation(value = "数据库表列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CodeView.TABLE_SCHEMA, value = "数据表结构", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CodeView.TABLE_NAME, value = "数据库名称", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/code/admin/table/v1/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> tableList(@ApiIgnore CodeView codeView) {

        validateRequest(
                codeView,
                CodeView.TABLE_SCHEMA,
                CodeView.TABLE_NAME
        );

        List<Code> resultList = codeService.tableSchemaList(codeView.getTableSchema(), codeView.getTableName());

        validateResponse(
                CodeView.TABLE_SCHEMA,
                CodeView.TABLE_NAME,
                CodeView.ENGINE,
                CodeView.TABLE_COMMENT,
                CodeView.SYSTEM_CREATE_TIME
        );

        return renderJson(resultList.size(), resultList);
    }

    @ApiOperation(value = "数据库表字段列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CodeView.TABLE_SCHEMA, value = "数据表结构", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CodeView.TABLE_NAME, value = "数据库名称", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/code/admin/table/field/v1/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> fieldlLst(@ApiIgnore CodeView codeView) {

        validateRequest(
                codeView,
                CodeView.TABLE_SCHEMA,
                CodeView.TABLE_NAME
        );

        List<Code> resultList = codeService.tableNameList(codeView.getTableSchema(), codeView.getTableName());

        validateResponse(
                CodeView.COLUMN_NAME,
                CodeView.COLUMN_KEY,
                CodeView.CHARACTER_MAXIMUM_LENGTH,
                CodeView.COLUMN_TYPE, CodeView.DATA_TYPE,
                CodeView.COLUMN_COMMENT
        );

        return renderJson(resultList);
    }

    @ApiOperation(value = "数据库表映射代码生成", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CodeView.TABLE_COMMENT, value = "数据库说明", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CodeView.TABLE_NAME, value = "数据库名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CodeView.AUTHOR, value = "开发者", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CodeView.COLUMN_LIST, value = "数据列表", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CodeView.IS_MQ, value = "是否消息队列", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/code/admin/v1/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> generate(@ApiIgnore CodeView codeView) {

        validateRequest(
                codeView,
                CodeView.TABLE_COMMENT,
                CodeView.TABLE_NAME,
                CodeView.AUTHOR,
                CodeView.COLUMN_LIST,
                CodeView.IS_MQ
        );

        try {
            String path = CodeController.class.getResource("/").toURI().getPath();
            path = new File(path).getParentFile().getCanonicalPath() + "/" + Constant.PUBLISH;
            String apiPath = path + "/api";
            String apiPackagePath = apiPath + "/" + codeView.getPackageName();
            String sysPath = path + "/sys";
            String sysPackagePath = sysPath + "/" + codeView.getPackageName();
            String webPath = path + "/web";
            String webPackagePath = webPath + "/" + codeView.getPackageName();
            String entityPath = sysPackagePath + "/entity";
            String sysRouterPath = sysPackagePath + "/router";
            String listenerPath = apiPackagePath + "/listener";
            String rpcPath = sysPackagePath + "/rpc";
            String sqlPath = apiPackagePath + "/sql";
            String mapperPath = apiPackagePath + "/mapper";
            String servicePath = apiPackagePath + "/service";
            String serviceImplPath = servicePath + "/impl";
            String controllerPath = apiPackagePath + "/controller";
            String controllerAdminPath = controllerPath + "/admin";
            String controllerDesktopPath = controllerPath + "/desktop";
            String controllerMobilePath = controllerPath + "/mobile";
            String controllerSystemPath = controllerPath + "/system";
            String repositoryPath = apiPackagePath + "/repository";
            String sysviewPath = sysPackagePath + "/view";
            String storePath = webPackagePath + "/store";
            String routerPath = webPackagePath + "/router";
            String viewPath = webPackagePath + "/view";

            FileUtil.createPath(path);
            FileUtil.createPath(apiPath);
            FileUtil.createPath(apiPackagePath);
            FileUtil.createPath(sysPath);
            FileUtil.createPath(sysPackagePath);
            FileUtil.createPath(webPath);
            FileUtil.createPath(webPackagePath);
            FileUtil.createPath(entityPath);
            if (codeView.getIsMq()) {
                FileUtil.createPath(sysRouterPath);
                FileUtil.createPath(listenerPath);
            }
            FileUtil.createPath(repositoryPath);
            FileUtil.createPath(rpcPath);
            FileUtil.createPath(sqlPath);
            FileUtil.createPath(mapperPath);
            FileUtil.createPath(servicePath);
            FileUtil.createPath(serviceImplPath);
            FileUtil.createPath(controllerPath);
            FileUtil.createPath(controllerAdminPath);
            FileUtil.createPath(controllerDesktopPath);
            FileUtil.createPath(controllerMobilePath);
            FileUtil.createPath(controllerSystemPath);
            FileUtil.createPath(sysviewPath);
            FileUtil.createPath(storePath);
            FileUtil.createPath(routerPath);
            FileUtil.createPath(viewPath);

            List<JSONObject> columnList = new ArrayList<JSONObject>(Constant.DEFAULT_LOAD_FACTOR);
            List<JSONObject> searchColumnList = new ArrayList<JSONObject>(Constant.DEFAULT_LOAD_FACTOR);
            List<JSONObject> listColumnList = new ArrayList<JSONObject>(Constant.DEFAULT_LOAD_FACTOR);
            List<JSONObject> detailColumnList = new ArrayList<JSONObject>(Constant.DEFAULT_LOAD_FACTOR);

            String upperTableName = codeView.getTableName().toUpperCase();
            String lowerEntityName = Util.repalceLast(Util.repalceLast(codeView.getTableName(), "_info", ""), "_map", "").toLowerCase();
            String upperEntityName = lowerEntityName.toUpperCase();
            String urlEntityName = lowerEntityName.replaceAll("_", "/");
            String firstLowerEntityName = lowerEntityName.substring(0, 1).toLowerCase() + lowerEntityName.substring(1);
            String firstUpperEntityName = lowerEntityName.substring(0, 1).toUpperCase() + lowerEntityName.substring(1);
            String firstLowerWithoutUnderlineEntityName = removeUnderline(firstLowerEntityName);
            String firstUpperWithoutUnderlineEntityName = removeUnderline(firstUpperEntityName);
            String tableId = "";
            String tableIdComment = "";
            Boolean isDate = false;

            String viewPackagePath = viewPath + "/" + firstLowerWithoutUnderlineEntityName;
            FileUtil.createPath(viewPackagePath);

            JSONArray jsonArray = JSONArray.parseArray(codeView.getColumnList());
            System.out.println(jsonArray.toJSONString());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject column = jsonArray.getJSONObject(i);

                String columnName = column.getString(CodeView.COLUMN_NAME);
                String dataType = column.getString(CodeView.DATA_TYPE);

                if (Util.isNullOrEmpty(column.getString(CodeView.CHARACTER_MAXIMUM_LENGTH))) {
                    String length = column.getString(CodeView.COLUMN_TYPE).replace(dataType, "").replace("(", "").replace(")", "");

                    if ("".equals(length) || length.contains(",")) {
                        length = "0";
                    }

                    column.put(CodeView.CHARACTER_MAXIMUM_LENGTH, length);
                } else {
                    if ("longtext".equals(dataType)) {
                        column.put(CodeView.CHARACTER_MAXIMUM_LENGTH, "0");
                    }
                }

                if ("PRI".equals(column.getString(CodeView.COLUMN_KEY))) {
                    tableId = columnName;
                    tableIdComment = column.getString(CodeView.COLUMN_COMMENT);
                }

                column.put("firstUpperColumnName", columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
                column.put("upperWithUnderlineColumnName", insertUnderline(columnName));
                column.put(CodeView.DATA_TYPE, dataType.toUpperCase());
                column.put("upperColumnName", columnName.toUpperCase());

                if (!column.getString(CodeView.COLUMN_NAME).startsWith("system")) {
                    columnList.add(column);
                }

                if (column.getBoolean("isSearch")) {
                    searchColumnList.add(column);
                }

                if (column.getBoolean("isList")) {
                    listColumnList.add(column);
                }

                if (column.getBoolean("isDetail")) {
                    detailColumnList.add(column);
                }

                if (dataType.toLowerCase().equals("datetime") || dataType.toLowerCase().equals("date")) {
                    if (!column.getString(CodeView.COLUMN_NAME).startsWith("system")) {
                        isDate = true;
                    }
                }
            }

            String firstUpperTableId = "";
            if (!Util.isNullOrEmpty(tableId)) {
                firstUpperTableId = tableId.substring(0, 1).toUpperCase() + tableId.substring(1);
            }
            String upperTableId = tableId.toUpperCase();
            String upperWithUnderlineTableId = insertUnderline(tableId);

            Map<String, Object> templateMap = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
            templateMap.put("tableComment", codeView.getTableComment());
            templateMap.put("moduleName", codeView.getModuleName());
            templateMap.put("packageName", codeView.getPackageName());
            templateMap.put("tableName", codeView.getTableName());
            templateMap.put("author", codeView.getAuthor());
            templateMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            templateMap.put("upperTableName", upperTableName);
            templateMap.put("tableId", tableId);
            templateMap.put("firstUpperTableId", firstUpperTableId);
            templateMap.put("upperTableId", upperTableId);
            templateMap.put("upperWithUnderlineTableId", upperWithUnderlineTableId);
            templateMap.put("tableIdComment", tableIdComment);
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
            templateMap.put("isDate", isDate);

            write(templateMap, "entity.txt", entityPath + "/" + firstUpperWithoutUnderlineEntityName + ".java");
            if (codeView.getIsMq()) {
                write(templateMap, "apiRoute.txt", sysRouterPath + "/" + firstUpperWithoutUnderlineEntityName + "Router.java");
                write(templateMap, "saveListener.txt", listenerPath + "/" + firstUpperWithoutUnderlineEntityName + "V1SaveListener.java");
                write(templateMap, "updateListener.txt", listenerPath + "/" + firstUpperWithoutUnderlineEntityName + "V1UpdateListener.java");
                write(templateMap, "deleteListener.txt", listenerPath + "/" + firstUpperWithoutUnderlineEntityName + "V1DeleteListener.java");
            }
            write(templateMap, "rpc.txt", rpcPath + "/" + firstUpperWithoutUnderlineEntityName + "Rpc.java");
            write(templateMap, "sql.txt", sqlPath + "/" + firstUpperWithoutUnderlineEntityName + "Sql.xml");
            write(templateMap, "mapper.txt", mapperPath + "/" + firstUpperWithoutUnderlineEntityName + "Mapper.java");
            write(templateMap, "service.txt", servicePath + "/" + firstUpperWithoutUnderlineEntityName + "Service.java");
            write(templateMap, "serviceImpl.txt", serviceImplPath + "/" + firstUpperWithoutUnderlineEntityName + "ServiceImpl.java");
            write(templateMap, "controllerAdmin.txt", controllerAdminPath + "/" + firstUpperWithoutUnderlineEntityName + "AdminController.java");
            write(templateMap, "controllerDesktop.txt", controllerDesktopPath + "/" + firstUpperWithoutUnderlineEntityName + "DesktopController.java");
            write(templateMap, "controllerMobile.txt", controllerMobilePath + "/" + firstUpperWithoutUnderlineEntityName + "MobileController.java");
            write(templateMap, "controllerSystem.txt", controllerSystemPath + "/" + firstUpperWithoutUnderlineEntityName + "SystemController.java");
            write(templateMap, "repository.txt", repositoryPath + "/" + firstUpperWithoutUnderlineEntityName + "Repository.java");
            write(templateMap, "sysView.txt", sysviewPath + "/" + firstUpperWithoutUnderlineEntityName + "View.java");
            write(templateMap, "store.txt", storePath + "/" + firstLowerWithoutUnderlineEntityName + ".js");
            write(templateMap, "router.txt", routerPath + "/" + firstLowerWithoutUnderlineEntityName + ".js");
            write(templateMap, "index.txt", viewPackagePath + "/" + "Index.js");
            write(templateMap, "detail.txt", viewPackagePath + "/" + "Detail.js");

        } catch (URISyntaxException e) {
            e.printStackTrace();

            throw new BusinessException("代码生成发生错误");
        } catch (IOException e) {
            e.printStackTrace();

            throw new BusinessException("代码生成发生错误");
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

        return sb.toString();
    }

    private String removeUnderline(String name) {
        String underline = "_";
        if (name.contains(underline)) {
            int index = name.indexOf(underline);
            name = name.substring(0, index) + name.substring(index + 1, index + 2).toUpperCase() + name.substring(index + 2);

            if (name.contains(underline)) {
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