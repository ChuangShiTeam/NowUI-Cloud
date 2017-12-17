package com.nowui.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

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
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext requestContext = RequestContext.getCurrentContext();
            InputStream stream = requestContext.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));

            if (StringUtils.isNotBlank(body)) {

            }

            System.out.println(body);


            RequestContext context = RequestContext.getCurrentContext();
            if (context.sendZuulResponse()) {
                requestContext.setResponseBody(body);
            } else {
                requestContext.setResponseBody(context.getResponseBody());
            }
        } catch (IOException e) {
            rethrowRuntimeException(e);
        }

        return null;
    }

}
