package com.nowui.cloud.zuul.util;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhongYongQiang
 */
public class Util {

    public static Boolean shouldFilter(RequestContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String url = "/upload";
        if (request.getRequestURI().startsWith(url)) {
            return false;
        }
        return true;
    }

}
