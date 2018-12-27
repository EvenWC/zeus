package com.wangcheng.zeus.core.config.utils;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: evan
 * @date: 2018/11/6 21:00
 * @description:
 */
public class HttpResponseUtils {

    private HttpResponseUtils() throws IllegalAccessException {
        throw new IllegalAccessException("工具类不能实例化");
    }

    public static void printJson(HttpServletResponse response ,Object object) throws IOException {
        if(Objects.isNull(object)){
            throw new IllegalArgumentException("object is null");
        }
        String value = JsonUtils.writeValueAsString(object);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(value.getBytes());
    }

}
