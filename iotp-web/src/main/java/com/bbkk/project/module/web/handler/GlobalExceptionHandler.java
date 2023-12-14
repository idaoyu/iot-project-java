package com.bbkk.project.module.web.handler;

import com.bbkk.project.exception.BizException;
import com.bbkk.project.exception.UploadFileException;
import com.bbkk.project.module.web.data.ResultBody;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Set;

import static com.bbkk.project.constant.WebRequestErrorCodeConstant.*;


/**
 * 系统异常处理器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-29 21:35
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数校验失败异常处理器
     *
     * @param ex BindException
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = BindException.class)
    public ResultBody requestParamsExceptionHandler(BindException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResultBody.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), errorMessage);
    }

    /**
     * 参数校验失败异常处理器
     *
     * @param ex ValidationException
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = ValidationException.class)
    public ResultBody validationExceptionHandler(ValidationException ex) {
        if (ex instanceof ConstraintViolationException realException) {
            if (realException.getConstraintViolations() == null) {
                return ResultBody.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), realException.getMessage());
            }
            Set<ConstraintViolation<?>> constraintViolations = realException.getConstraintViolations();
            if (constraintViolations.iterator().hasNext()) {
                ConstraintViolation<?> violation = constraintViolations.iterator().next();
                return ResultBody.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), violation.getMessage());
            }
        }
        return ResultBody.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), REQUEST_PARAMS_EXCEPTION.getErrorMessage());
    }

    /**
     * 业务异常处理器
     *
     * @param ex BizException
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = BizException.class)
    public ResultBody bizExceptionHandler(BizException ex) {
        return ResultBody.error(BIZ_EXCEPTION.getErrorCode(), ex.getMessage());
    }

    /**
     * 404 异常处理器
     *
     * @param ex NoHandlerFoundException
     * @return CommonResultDTO
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultBody notFountExceptionHandler(NoHandlerFoundException ex) {
        String message = ex.getRequestURL() + " 404";
        return ResultBody.error(NOT_FOUNT_EXCEPTION.getErrorCode(), message);
    }

    /**
     * 上传文件异常处理器
     *
     * @param ex UploadFileException
     * @return ResultBody
     */
    @ExceptionHandler(value = UploadFileException.class)
    public ResultBody uploadFileExceptionHandler(UploadFileException ex) {
        log.error("上传文件时产生异常", ex);
        return ResultBody.error(UPLOAD_FILE_EXCEPTION.getErrorCode(), UPLOAD_FILE_EXCEPTION.getErrorMessage());
    }

    /**
     * 默认的系统异常处理器
     *
     * @param ex Exception
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = Exception.class)
    public ResultBody defaultExceptionHandler(Exception ex) {
        log.info("系统出现未知异常", ex);
        return ResultBody.error(DEFAULT_EXCEPTION.getErrorCode(), DEFAULT_EXCEPTION.getErrorMessage());
    }

}
