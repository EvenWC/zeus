package com.wangcheng.zeus.common.filter;


import com.wangcheng.zeus.common.utils.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author: Administrator
 * @date: 2018/11/14 21:56
 * @description:
 */
public class ImmutableInputStreamServletRequest extends HttpServletRequestWrapper {

    /**
     * 缓存inputStream
     */
    private final byte[] body;

    public ImmutableInputStreamServletRequest(HttpServletRequest request) {
        super(request);
        try {
            body = IOUtils.toByteArray( request.getInputStream());
        } catch (IOException e) {
            throw new IllegalArgumentException("获取输入流失败");
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return super.getReader();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);

        return new ServletInputStream(){
            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}
