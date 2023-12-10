package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        // 获得目标方法的签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取对应方法上的注解
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        // 获得注解上的操作类型
        OperationType value = autoFill.value();
        // 获取目标方法的参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) return;

        // 拿到 插入/增加 方法的第一个对象参数
        Object targetObj = args[0];

        // 准备自动填充的值
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        if (value == OperationType.INSERT) {
            try {
                // 获得对应的set方法
                Method setCreateTime = targetObj.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setUpdateTime = targetObj.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setCreateUser = targetObj.getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateUser = targetObj.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                // 通过反射调用进行赋值
                setCreateTime.invoke(targetObj, now);
                setUpdateTime.invoke(targetObj, now);
                setCreateUser.invoke(targetObj, currentId);
                setUpdateUser.invoke(targetObj, currentId);
            } catch (Exception e) {
                log.info("功能字段自动填充失败，原因：{}",e.getMessage());
            }
        } else {
            try {
                Method setUpdateTime = targetObj.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = targetObj.getClass().getDeclaredMethod("setUpdateUser", Long.class);

                setUpdateTime.invoke(targetObj, now);
                setUpdateUser.invoke(targetObj, currentId);
            } catch (Exception e) {
                log.info("功能字段自动填充失败，原因：{}",e.getMessage());
            }
        }

    }
}
