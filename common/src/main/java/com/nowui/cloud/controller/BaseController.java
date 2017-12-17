package com.nowui.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.BaseException;
import com.nowui.cloud.util.ValidateUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import java.sql.SQLException;
import java.util.*;

/**
 * @author ZhongYongQiang
 */
public class BaseController {

    private String[] validateResponseColumnList = new String[]{};



    private Object checkResponse(int index, Object result) {
        index++;

        if (result instanceof BaseEntity) {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(result);

            Iterator iterator = jsonObject.entrySet().iterator();
            while (iterator.hasNext()) {
//                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
//                String key = entry.getKey();
//
//                Boolean isNotExit = true;
//                for (String column : validateResponseColumnList) {
//                    if (column.equals(key)) {
//                        isNotExit = false;
//                    }
//                }
//
//                if (isNotExit && index == 1) {
//                    iterator.remove();
//                } else {
//                    checkResponse(index, entry);
//                }

                iterator.remove();
            }
        } else if (result instanceof List) {
            checkResponseList(index, (List) result);
        } else if (result instanceof Map) {
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) result).entrySet()) {
                checkResponse(index, entry);
            }

            //checkMap(index, (Map) result);
        } else if (result instanceof Map.Entry) {
            if (((Map.Entry) result).getValue() instanceof String) {
                ((Map.Entry) result).setValue(StringEscapeUtils.unescapeHtml((String) ((Map.Entry) result).getValue()));
            } else if (((Map.Entry) result).getValue() instanceof List) {
                checkResponseList(index, (List) ((Map.Entry) result).getValue());
            }
        } else if (result instanceof String) {
            result = StringEscapeUtils.unescapeHtml((String) result);
        }

        return result;
    }

    private void checkResponseList(int index, List result) {
        for (Object item : result) {
            if (item instanceof BaseEntity) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(item);

                Iterator iterator = jsonObject.entrySet().iterator();
                while (iterator.hasNext()) {
//                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
//                    String key = entry.getKey();
//
//                    Boolean isNotExit = true;
//                    for (String column : validateResponseColumnList) {
//                        if (column.equals(key)) {
//                            isNotExit = false;
//                        }
//                    }
//
//                    if (isNotExit && index == 1) {
//                        iterator.remove();
//                    } else {
//                        checkResponse(index, entry);
//                    }
                    iterator.remove();

                }
            } else if (item instanceof Map) {
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) item).entrySet()) {
                    checkResponse(index, entry);
                }
            }
        }
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, 400);
        map.put(Constant.MESSAGE, e.toString());
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class, SQLException.class, MyBatisSystemException.class})
    public Map<String, Object> handleException(Exception e) {
        e.printStackTrace();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE,500);
        map.put(Constant.MESSAGE, "网络出现错误");
        return map;
    }

    public void validateRequest(BaseEntity entity, String... columns) {
        for (String column : columns) {
            Set<ConstraintViolation<BaseEntity>> constraintViolations = ValidateUtil.getValidator().validateProperty(entity, column);

            Iterator<ConstraintViolation<BaseEntity>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<BaseEntity> constraintViolation = iterator.next();
                throw new BaseException(constraintViolation.getMessage());
            }
        }
    }

    public void validateResponse(String... columns) {
        validateResponseColumnList = columns;
    }

    protected Map<String, Object> renderJson(Object data) {
//        data = checkResponse(0, data);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, 200);
        map.put(Constant.DATA, data);
        return map;
    }

    protected Map<String, Object> renderJson(int total, Object data) {
//        data = checkResponse(0, data);

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(Constant.TOTAL, total);
        dataMap.put(Constant.LIST, data);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, 200);
        map.put(Constant.DATA, dataMap);
        return map;
    }

}