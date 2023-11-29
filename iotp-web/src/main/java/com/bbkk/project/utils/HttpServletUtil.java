package com.bbkk.project.utils;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 处理 HttpServletRequest/HttpServletResponse 的 工具类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-29 13:17
 **/
public class HttpServletUtil {

    public static void writeHttpServletResponse(HttpServletResponse response, Object obj) throws IOException {
        String bodyString = JSONObject.toJSONString(obj);
        response.setHeader("Content-Type", "application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(bodyString);
    }

}
