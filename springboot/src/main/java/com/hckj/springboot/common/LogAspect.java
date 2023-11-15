package com.hckj.springboot.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Log;
import com.hckj.springboot.service.LogService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect // 表示 LogAspect 类是一个切面类，用于定义横切关注点（cross-cutting concerns），在这里是用于日志记录。
public class LogAspect {
    @Resource
    private LogService logService;
    @Around("@annotation(autoLog)")//使用 @Around 注解指定在目标方法执行前和执行后都会执行的通知。@annotation(autoLog) 表示这个通知会织入那些被标记了@AutoLog 注解的方法。
    public Object doAround(ProceedingJoinPoint joinPoint,AutoLog autoLog)throws Throwable{//joinPoint 是Spring AOP提供的一个接口，用于访问被通知方法的信息。
        String name = autoLog.value();//在注解里定义了value()
        String time = DateUtil.now();// 操作时间（当前时间)
        String username = "";//// 操作人
        Admin user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNotNull(user)) {
            username = user.getName();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//通过RequestContextHolder获取当前请求的上下文信息,然后，执行了被通知方法，获取了方法的返回结果 Result。
        String ip = request.getRemoteAddr();// 操作人IP
        //前面是切面前执行
        Result result = (Result) joinPoint.proceed();// 执行具体的接口(开始去执行注解的方法的内容)
        //后面是切面后执行
        Object data = result.getData();
        if (data instanceof Admin) {//登录操作，没有从token中拿到name，所以接口执行完了再那name。
            Admin admin = (Admin) data;
            username = admin.getName();
        }
        Log log = new Log(null, name, time, username, ip);//去往日志表里写一条日志记录,admin实体类要有构造方法
        logService.add(log);
        return result;

    };
}
