package com.netcommon.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/***
 * 日志aop
 * 在接口方法
 */
//@Component
//@Aspect
public class LogHandleAop {
//    private static final Logger logger = org.slf4j.LoggerFactory.getLogger("StockAnalysis");

//    @Pointcut("execution(* com.vip.vis.stock.analysis.modules.pr.business.impl.*.*(..))")
//    private void pointCutMethod() {
//    }
//
//    {
//        System.out.println("init .......");
//    }
//
//    @Around("pointCutMethod()")
//    public Object aroundLog(ProceedingJoinPoint jPoint) throws Throwable {
//        System.out.println("around .....");
//        Class clazz = jPoint.getTarget().getClass();
//        Method[] methods = clazz.getDeclaredMethods();
//        Object[] args = jPoint.getArgs();
//        for (Method method : methods) {
//            boolean flag = method.isAnnotationPresent(VisLog.class);
//            if (flag) {
//                System.out.println("接口名：" + clazz.getName());
//                System.out.println("方法名" + method.getName());
//                System.out.println("入参" + args);
//            }
//        }
//        Object ret = jPoint.proceed(args);
//        return ret;
//    }


    @Before("execution(* com..*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("before.....");
        String methodNames = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        System.out.println("The methods " + methodNames + " begins with" + Arrays.asList(args));

    }

}
