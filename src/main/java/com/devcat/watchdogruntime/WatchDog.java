package com.devcat.watchdogruntime;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;

/**
 * Created by xpchi on 2018/5/6.
 */

@Aspect
public class WatchDog {


   /* @Pointcut("execution(@com.devcat.annotations.DebugLog * *(..))")
    public void method() {}

    @Around("method()")
    public Object watchMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        Class<?> clz = signature.getDeclaringType();
        String methodName = signature.getName();

        long startTime = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.nanoTime();
        long time = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        StringBuilder builder = new StringBuilder();
        builder.append("Execute ")
                .append(methodName)
                .append(" [")
                .append(time)
                .append("ms]");
        Log.v(asTag(clz), builder.toString());
        return result;
    }*/

    @Pointcut("execution(@com.devcat.annotations.DebugLog * *(..))")
    public void method() {}


    @Around("method()")
    public Object aroundMethod2(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        Class<?> clz = signature.getDeclaringType();
        String methodName = signature.getName();


        long startTime = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.nanoTime();
        long time = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        StringBuilder builder = new StringBuilder();
        builder.append("Execute ")
                .append(methodName)
                .append(" [")
                .append(time)
                .append("ms]");
        Log.i(asTag(clz), builder.toString());

        return result;
    }

    private String asTag(Class<?> clz) {
        if (clz.isAnonymousClass()) { // 该类是匿名内部类
            asTag(clz.getEnclosingClass()); // 获取定义该成员内部类的类
        }
        return clz.getSimpleName();
    }
}
