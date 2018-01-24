package com.nowui.cloud.zuul.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.util.AesUtil;
import com.nowui.cloud.util.Util;

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
        return com.nowui.cloud.zuul.util.Util.shouldFilter(RequestContext.getCurrentContext());
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String contentType = request.getContentType();

        String jsonType = "application/json";
        String formDataType = "multipart/form-data";
        if (contentType.contains(jsonType)) {
            String requestBody = Util.readData(request);

            System.out.println(requestBody);

//            SortedMap<String, Object> jsonMap = JSON.parseObject(requestBody, new TypeReference<TreeMap<String, Object>>() {
//
//            });
//
//            StringBuilder signStringBuilder = new StringBuilder();
//            for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
//                if (entry.getKey() != "sign") {
//                    signStringBuilder.append(entry.getKey());
//                    signStringBuilder.append(entry.getValue());
//                }
//            }

            JSONObject parameterJSONObject = JSON.parseObject(requestBody);

//            String signParameter = parameterJSONObject.getString("sign");
//            String sign = DigestUtils.md5Hex(signStringBuilder.toString());
//
//            if (!signParameter.equals(sign)) {
/////                System.out.println(signStringBuilder.toString());
//
//                Map<String, Object> map = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
//                map.put("code", 400);
//                map.put("message", "签名不对");
//
//                context.setSendZuulResponse(false);
//                context.setResponseStatusCode(200);
//                context.setResponseBody(JSON.toJSONString(map));
//            }

            ///
//            Date date = new Date();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            calendar.add(Calendar.YEAR, 1);
            //
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("userId", "ffb11c2d4a3043ec8eb28c8cca9d1fc8");
//            jsonObject.put("expireTime", calendar.getTime());
            //
//            try {
//                System.out.println(AesUtil.aesEncrypt(jsonObject.toJSONString(), "0123456789012345"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            String token = parameterJSONObject.getString("token");
            String systemRequestUserId = decipherToken(token, context);

            parameterJSONObject.put("systemRequestUserId", systemRequestUserId);
            parameterJSONObject.put("systemRequestIpAddress", Util.getIpAddress(request));

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

        } else if (contentType.contains(formDataType)) {
            MultipartHttpServletRequest multipartRequest =
                    WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            String token = multipartRequest.getParameter("token");
            String systemRequestUserId = decipherToken(token, context);
            List<String> params = new ArrayList<String>();
            params.add(systemRequestUserId);
            Map<String, List<String>> map = new HashMap<String, List<String>>(Constant.DEFAULT_LOAD_FACTOR);
            map.put("systemRequestUserId", params);
            context.setRequestQueryParams(map);
        }

        return null;
    }


    /**
     * 解密token
     *
     * @param token
     * @param context
     * @return systemUserId 请求用户编号
     */
    private String decipherToken(String token, RequestContext context) {
        if (token != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.parseObject(AesUtil.aesDecrypt(token, Constant.PRIVATE_KEY));
                return jsonObject.getString("userId");
            } catch (Exception e) {
                Map<String, Object> map = new HashMap<String, Object>(Constant.DEFAULT_LOAD_FACTOR);
                map.put("code", 400);
                map.put("message", "Token不对");

                context.setSendZuulResponse(false);
                context.setResponseStatusCode(200);
                context.setResponseBody(JSON.toJSONString(map));
            }
        }

        return null;
    }
}
