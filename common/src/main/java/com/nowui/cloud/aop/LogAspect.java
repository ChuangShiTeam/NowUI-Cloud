package com.nowui.cloud.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author ZhongYongQiang
 */
//@Aspect
//@Component
public class LogAspect {

    /**
     * 前置通知，方法调用前被调用
     *
     * @param joinPoint
     */
    @Before("execution(* com.nowui.cloud.*.*.controller..*.*(..))")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        System.out.println(signature.getName());
        //AOP代理类的名字
        System.out.println(signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
//        Enumeration<String> enumeration = request.getParameterNames();
//        Map<String, String> parameterMap = Maps.newHashMap();
//        while (enumeration.hasMoreElements()) {
//            String parameter = enumeration.nextElement();
//            parameterMap.put(parameter, request.getParameter(parameter));
//        }
//        String str = JSON.toJSONString(parameterMap);
//        if (obj.length > 0) {
//            System.out.println("请求的参数信息为：" + str);
//        }
    }

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("execution(* com.nowui.cloud.*.*.controller..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
        try {
            //obj之前可以写目标方法执行前的逻辑
            Object obj = proceedingJoinPoint.proceed();
            System.out.println(JSON.toJSONString(obj));
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
