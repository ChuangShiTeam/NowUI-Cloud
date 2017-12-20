package com.nowui.cloud.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.nowui.cloud.util.AesUtil;
import com.nowui.cloud.util.Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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

        String requestBody = Util.readData(request);

        SortedMap<String, Object> jsonMap = JSON.parseObject(requestBody, new TypeReference<TreeMap<String, Object>>() {

        });

        StringBuilder signStringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            if (entry.getKey() != "sign") {
                signStringBuilder.append(entry.getKey());
                signStringBuilder.append(entry.getValue());
            }
        }

        JSONObject parameterJSONObject = JSON.parseObject(requestBody);

        String signParameter = parameterJSONObject.getString("sign");
        String sign = DigestUtils.md5Hex(signStringBuilder.toString());

        if (!signParameter.equals(sign)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 400);
            map.put("message", "签名不对");

            context.setSendZuulResponse(false);
            context.setResponseStatusCode(200);
            context.setResponseBody(JSON.toJSONString(map));
        }

        String systemRequestUserId = "";

//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.YEAR, 1);
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userId", "ffb11c2d4a3043ec8eb28c8cca9d1fc8");
//        jsonObject.put("expireTime", calendar.getTime());
//
//        try {
//            System.out.println(AesUtil.aesEncrypt(jsonObject.toJSONString(), "0123456789012345"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String token = parameterJSONObject.getString("token");
        if (token != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.parseObject(AesUtil.aesDecrypt(token, "0123456789012345"));
                systemRequestUserId = jsonObject.getString("userId");
            } catch (Exception e) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("code", 400);
                map.put("message", "Token不对");

                context.setSendZuulResponse(false);
                context.setResponseStatusCode(200);
                context.setResponseBody(JSON.toJSONString(map));
            }
        }

        parameterJSONObject.put("systemRequestUserId", systemRequestUserId);

        final byte[] reqBodyBytes = parameterJSONObject.toJSONString().getBytes();
        context.setRequest(new HttpServletRequestWrapper(context.getRequest()) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStreamWrapper(reqBodyBytes);
            }

            @Override
            public int getContentLength() {
                return reqBodyBytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return reqBodyBytes.length;
            }
        });

        return null;
    }
}
