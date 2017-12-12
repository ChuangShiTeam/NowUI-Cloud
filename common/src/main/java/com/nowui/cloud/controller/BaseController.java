package com.nowui.cloud.controller;

import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.BaseException;
import com.nowui.cloud.util.ValidateUtil;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author ZhongYongQiang
 */
public class BaseController {

    public void validateRequest(BaseEntity entity, String... columns) {
        for (String column : columns) {
            Set<ConstraintViolation<BaseEntity>> constraintViolations = ValidateUtil.getValidator().validateProperty(
                    entity,
                    column
            );

            Iterator<ConstraintViolation<BaseEntity>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<BaseEntity> constraintViolation = iterator.next();
                throw new RuntimeException(constraintViolation.getMessage());
            }
        }
    }

    public void validateResponse(String... columns) {

    }

    protected Map<String, Object> renderJson(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, 200);
        map.put(Constant.DATA, data);
        return map;
    }

    protected Map<String, Object> renderJson(int total, Object data) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(Constant.TOTAL, total);
        dataMap.put(Constant.LIST, data);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, 200);
        map.put(Constant.DATA, dataMap);
        return map;
    }

}
