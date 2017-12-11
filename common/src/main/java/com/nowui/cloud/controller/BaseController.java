package com.nowui.cloud.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhongYongQiang
 */
public class BaseController {

    public void validateRequest(String... keys) {

    }

    public void validateResponse(String... keys) {

    }

    protected Map<String, Object> renderJson(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("data", data);
        return map;
    }

    protected Map<String, Object> renderJson(int total, Object data) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("total", total);
        dataMap.put("list", data);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("data", dataMap);
        return map;
    }

}
