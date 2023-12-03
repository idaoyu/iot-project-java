package com.bbkk.project.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

/**
 * Validated 校验工具类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 17:29
 **/
public class ValidatedUtil {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }


}
