package com.nowui.cloud.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

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

        SortedMap<String, Object> jsonMap = JSON.parseObject(body, new TypeReference<TreeMap<String, Object>>() {

        });

        StringBuilder signStringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            if (!entry.getKey().equals("sign")) {
                signStringBuilder.append(entry.getKey());
                signStringBuilder.append(entry.getValue());
            }
        }
        System.out.println(signStringBuilder.toString());

        String signParameter = parameterJSONObject.getString("sign");
        String sign = DigestUtils.md5Hex(signStringBuilder.toString());

        if (!signParameter.equals(sign)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 400);
            map.put("message", "签名不对");

            System.out.println(signParameter);
            System.out.println(sign);

            context.setSendZuulResponse(false);
            context.setResponseStatusCode(200);
            context.setResponseBody(JSON.toJSONString(map));
        }

        String httpUrl = request.getRequestURI();

//        System.out.println("----------------------------------------------------------------------------------------------------------------");
//        System.out.println("url: " + httpUrl);
//        System.out.println("time: " + DateUtil.getDateTimeString(http.getSystem_create_time()));
//        System.out.println("app_id: " + app_id);
//        System.out.println("user_id: " + http.getSystem_create_user_id());
//        System.out.println("http_token: " + http.getHttp_token());
//        System.out.println("request: " + http.getHttp_request());
//        System.out.println("response: " + http.getHttp_response());
//        System.out.println("----------------------------------------------------------------------------------------------------------------");

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
