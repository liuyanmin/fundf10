package com.lym.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by liuyanmin on 2019/9/29.
 */
public class HttpServletResponseUtil {

    private static String UTF8 = "UTF-8";
    private static String CONTENT_TYPE = "application/json";

    private HttpServletResponseUtil(){
        throw new AssertionError();
    }

    public static void printJSON(HttpServletResponse response, Object object) throws Exception{
        String str = JSON.toJSONString(object);
        printStr(response, str);
    }

    public static void printStr(HttpServletResponse response, String str) throws Exception{
        response.setCharacterEncoding(UTF8);
        response.setContentType(CONTENT_TYPE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(str);
        printWriter.flush();
        printWriter.close();
    }

}
