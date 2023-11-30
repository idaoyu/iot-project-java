package com.bbkk.project.exception;

/**
 * 业务逻辑异常
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-30 18:55
 **/
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }
}
