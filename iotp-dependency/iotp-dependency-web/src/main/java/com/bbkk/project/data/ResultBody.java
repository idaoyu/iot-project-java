package com.bbkk.project.data;

import lombok.Builder;
import lombok.Data;

/**
 * 接口的返回值包装类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 18:21
 **/
@Data
@Builder
public class ResultBody {

    /**
     * 调用是否成功
     */
    private Boolean success;

    /**
     * 响应的数据
     */
    private Object data;

    /**
     * 如果发生错误，错误的状态码
     */
    private String errorCode;

    /**
     * 如果发生错误，错误的信息
     */
    private String errorMessage;

    /**
     * 接口调用的唯一 id
     */
    private String traceId;

    public static ResultBody ok(Object data) {
        return ResultBody.builder()
                .success(true)
                .data(data)
                .build();
    }

    public static ResultBody error(String errorCode, String errorMessage) {
        return ResultBody.builder()
                .success(false)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

}
