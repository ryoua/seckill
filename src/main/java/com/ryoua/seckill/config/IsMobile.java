package com.ryoua.seckill.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author ryoua Created on 2019-07-08
 */
@Target({ElementType.METHOD, ElementType.FIELD,
ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {
    boolean required() default true;

    String message() default "手机号码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
