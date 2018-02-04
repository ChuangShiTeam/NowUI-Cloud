package com.nowui.cloud.annotation;

import java.lang.annotation.*;

/**
 * 产品
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface KeyId {

}