package com.nowui.cloud.base.code.controller.admin;

import java.io.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.code.entity.Code;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.shop.product.rpc.ProductRpc;
import com.nowui.cloud.util.FileUtil;
import com.nowui.cloud.util.Util;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.code.service.CodeService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;

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
    private ProductRpc productRpc;

    @ApiOperation(value = "测试")
    @RequestMapping(value = "/code/admin/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        Code codeEntity = getEntry(Code.class);

///        System.out.println(JSON.toJSONString(productRpc.find("c01e2a21271e433dac70c561d06cfe9c")));

//        productMq.sendBar2Rabbitmq("123456789");

        return "Hello World!";
    }

    @ApiOperation(value = "数据库表列表")
    @RequestMapping(value = "/code/admin/table/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> tableList() {
        Code codeEntity = getEntry(Code.class);

        validateRequest(codeEntity, Code.TABLE_SCHEMA, Code.TABLE_NAME);

        List<Code> resultList = codeService.tableSchemaList(codeEntity.getTableSchema(), codeEntity.getTableName());

        validateResponse(Code.TABLE_SCHEMA, Code.TABLE_NAME, Code.ENGINE, Code.TABLE_COMMENT, Code.SYSTEM_CREATE_TIME);

        return renderJson(resultList.size(), resultList);
    }

    @ApiOperation(value = "数据库表字段列表")
    @RequestMapping(value = "/code/admin/table/field/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> fieldlLst() {
        Code codeEntity = getEntry(Code.class);

        validateRequest(codeEntity, Code.TABLE_SCHEMA, Code.TABLE_NAME);

        List<Code> resultList = codeService.tableNameList(codeEntity.getTableSchema(), codeEntity.getTableName());

        validateResponse(Code.COLUMN_NAME, Code.COLUMN_KEY, Code.CHARACTER_MAXIMUM_LENGTH, Code.COLUMN_TYPE, Code.DATA_TYPE, Code.COLUMN_COMMENT);

        return renderJson(resultList);
    }

    @ApiOperation(value = "数据库表映射代码生成")
    @RequestMapping(value = "/code/admin/generate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> generate() {
        Code codeEntity = getEntry(Code.class);

        validateRequest(codeEntity, Code.TABLE_COMMENT, Code.TABLE_NAME, Code.AUTHOR, Code.COLUMN_LIST);

        try {
            String path = CodeController.class.getResource("/").toURI().getPath();
            path = new File(path).getParentFile().getCanonicalPath() + "/" + Constant.PUBLISH;
            String apiPath = path + "/api";
            String apiPackagePath = apiPath + "/" + codeEntity.getPackageName();
            String sysPath = path + "/sys";
            String sysPackagePath = sysPath + "/" + codeEntity.getPackageName();
            String webPath = path + "/web";
            String webPackagePath = webPath + "/" + codeEntity.getPackageName();
            String entityPath = sysPackagePath + "/entity";
            String mqPath = sysPackagePath + "/mq";
            String mqImplPath = mqPath + "/impl";
            String rpcPath = sysPackagePath + "/rpc";
//            String rpcFallbackPath = rpcPath + "/fallback";
            String sqlPath = apiPackagePath + "/sql";
            String mapperPath = apiPackagePath + "/mapper";
            String servicePath = apiPackagePath + "/service";
            String serviceImplPath = servicePath + "/impl";
            String controllerPath = apiPackagePath + "/controller";
            String controllerAdminPath = controllerPath + "/admin";
            String controllerDesktopPath = controllerPath + "/desktop";
            String controllerMobilePath = controllerPath + "/mobile";
            String controllerSystemPath = controllerPath + "/system";
            String configPath = apiPackagePath + "/config";
            String listenerPath = apiPackagePath + "/listener";
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
            if (codeEntity.getIsMq()) {
                FileUtil.createPath(mqPath);
                FileUtil.createPath(mqImplPath);
                FileUtil.createPath(configPath);
                FileUtil.createPath(listenerPath);
            }
            FileUtil.createPath(rpcPath);
///            FileUtil.createPath(rpcFallbackPath);
            FileUtil.createPath(sqlPath);
            FileUtil.createPath(mapperPath);
            FileUtil.createPath(servicePath);
            FileUtil.createPath(serviceImplPath);
            FileUtil.createPath(controllerPath);
            FileUtil.createPath(controllerAdminPath);
            FileUtil.createPath(controllerDesktopPath);
            FileUtil.createPath(controllerMobilePath);
            FileUtil.createPath(controllerSystemPath);
            FileUtil.createPath(storePath);
            FileUtil.createPath(routerPath);
            FileUtil.createPath(viewPath);

            List<Code> codeList = JSONArray.parseArray(codeEntity.getColumnList().toJSONString(), Code.class);

            List<JSONObject> columnList = new ArrayList<JSONObject>();
            List<JSONObject> searchColumnList = new ArrayList<JSONObject>();
            List<JSONObject> listColumnList = new ArrayList<JSONObject>();
            List<JSONObject> detailColumnList = new ArrayList<JSONObject>();

            String upperTableName = codeEntity.getTableName().toUpperCase();
            String lowerEntityName = Util.repalceLast(Util.repalceLast(Util.repalceLast(codeEntity.getTableName(), "_info", ""), "_map", ""), "count", "").toLowerCase();
            String upperEntityName = lowerEntityName.toUpperCase();
            String urlEntityName = lowerEntityName.replaceAll("_", "/");
            String firstLowerEntityName = lowerEntityName.substring(0, 1).toLowerCase() + lowerEntityName.substring(1);
            String firstUpperEntityName = lowerEntityName.substring(0, 1).toUpperCase() + lowerEntityName.substring(1);
            String firstLowerWithoutUnderlineEntityName = removeUnderline(firstLowerEntityName);
            String firstUpperWithoutUnderlineEntityName = removeUnderline(firstUpperEntityName);
            String tableId = "";

            String viewPackagePath = viewPath + "/" + firstLowerWithoutUnderlineEntityName;
            FileUtil.createPath(viewPackagePath);

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

                if ("PRI".equals(code.getColumnKey())) {
                    tableId = columnName;
                }

                code.put("firstUpperColumnName", columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
                code.put("upperWithUnderlineColumnName", insertUnderline(columnName));
                code.setDataType(dataType.toUpperCase());
                code.put("upperColumnName", columnName.toUpperCase());

                if (!code.getColumnName().startsWith("system")) {
                    columnList.add(code);
                }

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
            String upperWithUnderlineTableId = insertUnderline(tableId);

            Map<String, Object> templateMap = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
            templateMap.put("tableComment", codeEntity.getTableComment());
            templateMap.put("moduleName", codeEntity.getModuleName());
            templateMap.put("packageName", codeEntity.getPackageName());
            templateMap.put("tableName", codeEntity.getTableName());
            templateMap.put("author", codeEntity.getAuthor());
            templateMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            templateMap.put("upperTableName", upperTableName);
            templateMap.put("tableId", tableId);
            templateMap.put("firstUpperTableId", firstUpperTableId);
            templateMap.put("upperTableId", upperTableId);
            templateMap.put("upperWithUnderlineTableId", upperWithUnderlineTableId);
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
            if (codeEntity.getIsMq()) {
                write(templateMap, "mq.txt", mqPath + "/" + firstUpperWithoutUnderlineEntityName + "Mq.java");
                write(templateMap, "mqImpl.txt", mqImplPath + "/" + firstUpperWithoutUnderlineEntityName + "MqImpl.java");
            }
            write(templateMap, "rpc.txt", rpcPath + "/" + firstUpperWithoutUnderlineEntityName + "Rpc.java");
//            write(templateMap, "rpcFallback.txt", rpcFallbackPath + "/" + firstUpperWithoutUnderlineEntityName + "RpcFallback.java");
            write(templateMap, "sql.txt", sqlPath + "/" + firstUpperWithoutUnderlineEntityName + "Sql.xml");
            write(templateMap, "mapper.txt", mapperPath + "/" + firstUpperWithoutUnderlineEntityName + "Mapper.java");
            write(templateMap, "service.txt", servicePath + "/" + firstUpperWithoutUnderlineEntityName + "Service.java");
            write(templateMap, "serviceImpl.txt", serviceImplPath + "/" + firstUpperWithoutUnderlineEntityName + "ServiceImpl.java");
            write(templateMap, "controllerAdmin.txt", controllerAdminPath + "/" + firstUpperWithoutUnderlineEntityName + "AdminController.java");
            write(templateMap, "controllerDesktop.txt", controllerDesktopPath + "/" + firstUpperWithoutUnderlineEntityName + "DesktopController.java");
            write(templateMap, "controllerMobile.txt", controllerMobilePath + "/" + firstUpperWithoutUnderlineEntityName + "MobileController.java");
            write(templateMap, "controllerSystem.txt", controllerSystemPath + "/" + firstUpperWithoutUnderlineEntityName + "SystemController.java");
            if (codeEntity.getIsMq()) {
//                write(templateMap, "listener.txt", listenerPath + "/" + firstUpperWithoutUnderlineEntityName + "Listener.java");
                write(templateMap, "config.txt", configPath + "/" + firstUpperWithoutUnderlineEntityName + "Config.java");
                write(templateMap, "listener.txt", listenerPath + "/" + firstUpperWithoutUnderlineEntityName + "Listener.java");
            }
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
