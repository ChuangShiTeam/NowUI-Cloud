package com.nowui.cloud.controller;

import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.ibatis.binding.BindingException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.client.ClientException;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.exception.SystemException;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.util.ValidateUtil;
import com.nowui.cloud.view.BaseView;

/**
 * BaseController
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Transactional
public class BaseController {

    private String[] validateResponseColumnList = new String[]{};

    
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public <T> T getEntry(Class<T> clazz) {
        if (getRequest().getAttribute(Constant.REQUEST_BODY) == null) {
            getRequest().setAttribute(Constant.REQUEST_BODY, Util.readData(getRequest()));
        }
        return JSONObject.parseObject(Util.readData(getRequest()), clazz);
    }

    private JSONObject checkMap(Object data) {
        JSONObject result = (JSONObject) JSON.toJSON(data);

        Iterator iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
            String key = entry.getKey();

            Boolean isNotExit = true;
            for (String column : validateResponseColumnList) {
                if (column.equals(key)) {
                    isNotExit = false;
                }
            }

            if (isNotExit) {
                iterator.remove();
            } else {

            }
        }

        return result;
    }

    private Object checkFirstResponse(Object data) {
        if (data instanceof BaseEntity) {
            return checkMap(data);
        } else if (data instanceof List) {
            List list = new ArrayList();
            for (Object item : (List) data) {
                if (item instanceof BaseEntity) {
                    list.add(checkMap(item));
                } else if (item instanceof Map) {
                    list.add(checkMap(item));
                }
            }

            return list;
        } else if (data instanceof Boolean) {
            return data;
        } else if (data instanceof Map) {
            return checkMap(data);
        }

        return null;
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();

        Map<String, Object> map = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
        
        if (e instanceof BusinessException) {
            map.put(Constant.CODE, 400);
            map.put(Constant.MESSAGE, e.getMessage());
        } else if (e instanceof SystemException) {
            map.put(Constant.CODE, 500);
            map.put(Constant.MESSAGE, Constant.ERROR);
        } else {
            map.put(Constant.CODE, 500);
            map.put(Constant.MESSAGE, Constant.ERROR);
        }
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = {SQLException.class, MyBatisSystemException.class, RedisConnectionFailureException.class, IllegalStateException.class, BindingException.class, ClientException.class, SocketTimeoutException.class})
    public Map<String, Object> handleException(Exception e) {
        e.printStackTrace();

        Map<String, Object> map = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
        map.put(Constant.CODE, 500);
        map.put(Constant.MESSAGE, Constant.ERROR);
        return map;
    }

    public void validateRequest(BaseEntity entity, String... columns) {
        for (String column : columns) {
            Set<? extends ConstraintViolation<? extends BaseEntity>> constraintViolations = ValidateUtil.getValidator().validateValue(entity.getClass(), column, entity.get(column));

            Iterator<ConstraintViolation<BaseEntity>> iterator = (Iterator<ConstraintViolation<BaseEntity>>) constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<BaseEntity> constraintViolation = iterator.next();
                throw new BusinessException(constraintViolation.getMessage());
            }
        }
    }
    
    public void validateRequest(BaseView view, String... columns) {
        for (String column : columns) {
            Set<? extends ConstraintViolation<? extends BaseView>> constraintViolations = ValidateUtil.getValidator().validateValue(view.getClass(), column, view.get(column));

            Iterator<ConstraintViolation<BaseView>> iterator = (Iterator<ConstraintViolation<BaseView>>) constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<BaseView> constraintViolation = iterator.next();
                throw new BusinessException(constraintViolation.getMessage());
            }
        }
    }

    public void validateResponse(String... columns) {
        validateResponseColumnList = columns;
    }

    protected Map<String, Object> renderJson(Object data) {
        data = checkFirstResponse(data);

        Map<String, Object> map = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
        map.put(Constant.CODE, 200);
        map.put(Constant.DATA, data);
        return map;
    }

    protected Map<String, Object> renderJson(int total, Object data) {
        data = checkFirstResponse(data);

        Map<String, Object> dataMap = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
        dataMap.put(Constant.TOTAL, total);
        dataMap.put(Constant.LIST, data);

        Map<String, Object> map = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
        map.put(Constant.CODE, 200);
        map.put(Constant.DATA, dataMap);
        return map;
    }

    protected Map<String, Object> renderJson(long total, Object data) {
        data = checkFirstResponse(data);

        Map<String, Object> dataMap = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
        dataMap.put(Constant.TOTAL, total);
        dataMap.put(Constant.LIST, data);

        Map<String, Object> map = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
        map.put(Constant.CODE, 200);
        map.put(Constant.DATA, dataMap);
        return map;
    }

}