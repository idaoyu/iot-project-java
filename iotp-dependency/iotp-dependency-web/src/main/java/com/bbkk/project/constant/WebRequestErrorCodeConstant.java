package com.bbkk.project.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用请求错误代码
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-02 11:47
 **/
@Getter
@AllArgsConstructor
public enum WebRequestErrorCodeConstant {

    DEFAULT_EXCEPTION("500", "系统出现未知错误"),

    NOT_FOUNT_EXCEPTION("404", null),

    REQUEST_PARAMS_EXCEPTION("-1", "接口参数校验失败"),

    BIZ_EXCEPTION("-2", null),

    UPLOAD_FILE_EXCEPTION("-3", "文件上传失败"),
    ;

    /**
     * 错误代码
     */
    private final String errorCode;

    /**
     * 错误信息
     */
    private final String errorMessage;

}
