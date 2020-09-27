package com.ccic.salessapp.common.utils;

import com.ccic.salessapp.common.core.CoreErrorCode;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public final class ValidationUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
/***
 * Bean Validation 中内置的 constraint
 *
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 *
 * Hibernate Validator 附加的 constraint
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 */
    /****
     *
     * @param o
     */
    public static void validate(Object o, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o,groups);
        if (CollectionUtils.isNotEmpty(violations)) {
            List<String> validationDetail = new ArrayList<>();
            for (ConstraintViolation<Object> v : violations) {
                String error = v.getRootBeanClass().getSimpleName() + " - " + v.getPropertyPath() + " - " + v.getMessage();
                log.error(error);
                validationDetail.add(error);
                CoreErrorCode errorCode=CoreErrorCode.PARAMETER_ERROR;
                throw new PlatformBaseException(v.getMessage(), errorCode.getCode());
            }
//            throw new IllegalArgumentException(StringUtils.join(validationDetail));
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new PlatformBaseException(message, 10002);
        }
    }
}
