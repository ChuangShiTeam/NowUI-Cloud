package com.nowui.cloud.aop;

import com.nowui.cloud.entity.BaseEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ZhongYongQiang
 */
//@Aspect
//@Component
public class LogAspect {

    @Around("execution(* com.nowui.cloud.*.*.controller..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

        ((BaseEntity)args[0]).put(BaseEntity.SYSTEM_UPDATE_USER_ID, "456");

        return proceedingJoinPoint.proceed(args);
    }

}