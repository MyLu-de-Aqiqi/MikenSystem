package com.miken.sys.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringMvcUtil {
    public SpringMvcUtil() {
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new RuntimeException("当前环境非JavaWeb");
        } else {
            return servletRequestAttributes.getRequest();
        }
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new RuntimeException("当前环境非JavaWeb");
        } else {
            return servletRequestAttributes.getResponse();
        }
    }
}
