package com.nowui.cloud.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhongYongQiang
 */
@Component
public class RequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String body = readData(request);

        JSONObject parameterJSONObject = JSON.parseObject(body);
        parameterJSONObject.put("systemCreateUserId", "ZhongYongQiang");
        context.setResponseBody(parameterJSONObject.toJSONString());

        String appId = parameterJSONObject.getString("appId");

//        if (appId == null) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("code", 400);
//            map.put("message", "应用编号不能为空");
//
//            context.setSendZuulResponse(false);
//            context.setResponseStatusCode(200);
//            context.setResponseBody(JSON.toJSONString(map));
//            context.getResponse().setContentType("application/json;charset=utf-8");
//        }

        System.out.println(body);

        return null;
    }

    public static String readData(HttpServletRequest request) {
        BufferedReader br = null;

        try {
            StringBuilder result = new StringBuilder();

            String line;
            for (br = request.getReader(); (line = br.readLine()) != null; result.append(line)) {
                if (result.length() > 0) {
                    result.append("\n");
                }
            }

            line = result.toString();
            return line;
        } catch (IOException var12) {
            throw new RuntimeException(var12);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var11) {

                }
            }

        }
    }
}
