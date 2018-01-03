package com.nowui.cloud.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.nowui.cloud.util.DateUtil;
import com.nowui.cloud.util.Util;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

/**
 * @author ZhongYongQiang
 */
@Component
public class ResponseFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();  
        if (request.getRequestURI().startsWith("/upload")) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = null;
        try {
            context = RequestContext.getCurrentContext();
            InputStream stream = context.getResponseDataStream();
            String responseody = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            context.getResponse().setContentType("application/json;charset=utf-8");

            if (context.sendZuulResponse()) {

            } else {
                responseody = context.getResponseBody();
            }

            context.setResponseBody(responseody);
        } catch (IOException e) {
            rethrowRuntimeException(e);
        } finally {
            if (context != null) {
                HttpServletRequest request = context.getRequest();
                
                String contentType = request.getContentType();
                if (contentType.contains("application/json")) {
                    String requestBody = Util.readData(request);
                    JSONObject parameterJSONObject = JSON.parseObject(requestBody);
                    String url = request.getRequestURI();
                    String appId = parameterJSONObject.getString("appId");
                    String systemRequestUserId = parameterJSONObject.getString("systemRequestUserId");
                    String token = parameterJSONObject.getString("token");

                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                    System.out.println("url: " + url);
                    System.out.println("time: " + DateUtil.getDateTimeString(new Date()));
                    System.out.println("appId: " + appId);
                    System.out.println("userId: " + systemRequestUserId);
                    System.out.println("token: " + token);
                    System.out.println("request: " + parameterJSONObject);
                    System.out.println("response: " + context.getResponseBody());
                    System.out.println("----------------------------------------------------------------------------------------------------------------");

                } else {
                    
                }
            }
        }

        return null;
    }

}
