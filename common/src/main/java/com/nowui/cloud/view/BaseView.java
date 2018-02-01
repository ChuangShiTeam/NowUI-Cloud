package com.nowui.cloud.view;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.Serializable;

/**
 * baseView
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public class BaseView extends JSONObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableId;

    public String getTableId() {
        if (tableId == null) {
            java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                boolean isId = field.isAnnotationPresent(Id.class);
                if (isId) {
                    field.setAccessible(true);
                    tableId = field.getName();
                    return tableId;
                }
            }
        }
        return tableId;
    }

}
