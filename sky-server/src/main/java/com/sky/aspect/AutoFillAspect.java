package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Slf4j
@Aspect
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){
    }

    public void autoFill(JoinPoint joinPoint){

        log.info(".........开始自动填充的切面........");

        //获取当前被拦截方法的value
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();  //获取方法签名
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);  //获取方法上的注解
        OperationType operationType = autoFill.value();

        //获取当前被拦截方法的实体对象
        //目标方法运行参数
        Object[] args = joinPoint.getArgs();
        if(args.length == 0 || args[0] == null){
            return;
        }
        Object entity = args[0];

        //准备统一赋值数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //根据value赋值
        if(operationType == OperationType.INSERT){
            try {
                //反射调用set方法
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                //通过反射调用set方法，给实体对象的字段赋值
                setCreateTime.invoke(entity,now);
                setUpdateTime.invoke(entity,now);

            } catch (Exception e) {
                e.printStackTrace(); //日志记录
            }
        }

        if(operationType == OperationType.UPDATE){
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                setUpdateTime.invoke(entity,now);
            } catch (Exception e) {
                e.printStackTrace(); //日志记录
            }
        }
        log.info(".........自动填充的切面结束........");
    }
}
