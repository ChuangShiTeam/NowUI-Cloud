package com.nowui.cloud.filter;

import com.nowui.cloud.request.BodyReaderHttpServletRequestWrapper;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ZhongYongQiang
 */
@Component
@ServletComponentScan
@WebFilter(filterName = "httpServletRequestFilter", urlPatterns = "/*")
public class HttpServletRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest)servletRequest);

        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
