package com.bbkk.project.module.web;

import com.alibaba.fastjson2.JSONObject;
import com.bbkk.project.module.web.annotation.DisableResponseBodyAdvice;
import com.bbkk.project.module.web.data.ResultBody;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一响应体包装器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-29 20:28
 **/
@RestControllerAdvice(basePackages = "com.hope.project")
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 如果方法被 DisableResponseBodyAdvice 注解标注 则不对响应体进行包装
        DisableResponseBodyAdvice annotation = returnType.getAnnotatedElement().getAnnotation(DisableResponseBodyAdvice.class);
        return annotation == null;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return JSONObject.toJSONString(ResultBody.ok(body));
        }
        if (body instanceof ResultBody) {
            return body;
        }
        return ResultBody.ok(body);
    }
}
