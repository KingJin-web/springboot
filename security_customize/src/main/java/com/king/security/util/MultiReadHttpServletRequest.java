package com.king.security.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-12 08:16
 */
@Slf4j
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    private final byte[] body;

    public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        body = getBodyString(request).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
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

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    private String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将前端请求的表单数据转换成json字符串 - 前后端一体的情况下使用
     *
     * @param request:
     * @return: java.lang.String
     */
    public String getBodyJsonStrByForm(ServletRequest request) {
        Map<String, Object> bodyMap = new HashMap<>(16);
        try {
            // 参数定义
            String paraName = null;
            // 获取请求参数并转换
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                paraName = e.nextElement();
                bodyMap.put(paraName, request.getParameter(paraName));
            }
        } catch (Exception e) {
            log.error("请求参数转换错误!", e);
        }
        // json对象转json字符串 转javabean
//        SecurityUser user = JSONObject.parseObject(JSONObject.toJSONString(bodyMap), SecurityUser.class);
        // json对象转json字符串
        return JSONObject.toJSONString(bodyMap);
    }

    /**
     * 将前端传递的json数据转换成json字符串 - 前后端分离的情况下使用
     *
     * @param request:
     * @return: java.lang.String
     */
    public String getBodyJsonStrByJson(ServletRequest request) {
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (Exception e) {
            log.error("请求参数转换错误!", e);
        }
        return json.toString();
    }

}