package com.bbkk.project.data;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * web 接口全局通用返回包装对象
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-13 18:27
 **/
@Data
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 调用是否成功
     */
    private Boolean success;

    /**
     * 响应的数据
     */
    private T data;

    /**
     * 如果发生错误，错误的状态码
     */
    private String errorCode;

    /**
     * 如果发生错误，错误的信息
     */
    private String errorMessage;

    public static <T> R<T> ok(T data) {
        R<T> apiResult = new R<>();
        apiResult.setSuccess(true);
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> R<T> error(String errorCode, String errorMessage) {
        R<T> apiResult = new R<>();
        apiResult.setSuccess(false);
        apiResult.setErrorCode(errorCode);
        apiResult.setErrorMessage(errorMessage);
        return apiResult;
    }

}
