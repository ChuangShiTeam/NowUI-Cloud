package com.nowui.cloud.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author ZhongYongQiang
 */
public class LogAspect {

    @Around("execution(* com.nowui.cloud.*.*.controller..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

//        ((BaseEntity)args[0]).put(BaseEntity.SYSTEM_UPDATE_USER_ID, "456");

        return proceedingJoinPoint.proceed(args);
    }

}