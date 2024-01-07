package com.bbkk.project.exception;

/**
 * 文件上传失败异常
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 21:55
 **/
public class UploadFileException extends RuntimeException {

    public UploadFileException(String message) {
        super(message);
    }

}
