package com.jzt.sso.aspect;



import com.jzt.sso.annotation.SysLog;
import com.jzt.sso.model.SystemLogEntity;
import com.jzt.sso.model.UserEntity;
import com.jzt.sso.service.SystemLogService;
import com.jzt.sso.service.UserService;
import com.jzt.sso.utils.HttpContextUtils;
import com.jzt.sso.utils.IPUtils;
import com.jzt.sso.utils.UserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName SysLogAspect
 * @Description 系统日志切面
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SystemLogService sysLogService;

    @Autowired
    private UserService userService;


    @Pointcut("@annotation(com.jzt.sso.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SystemLogEntity sysLog = new SystemLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOpAction(syslog.value());
            sysLog.setOpEntity(syslog.moduleName());
        }

        //请求的方法名
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = signature.getName();
//        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
//        Object[] args = joinPoint.getArgs();
//        try {
//            String params = new Gson().toJson(args[0]);
//            sysLog.setParams(params);
//        } catch (Exception e) {
//
//        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
//        sysLog.setMacIp(IPUtils.getMACAddress());
        //用户名
       String username= UserUtil.getUserName();
        UserEntity currentUser = userService.selectByName(username);
        sysLog.setOpId(currentUser.getId());
        sysLog.setOpName(currentUser.getUserName());
        //获取机构id
        sysLog.setOpOrg(currentUser.getOrgName());

        sysLog.setOpTime(String.valueOf(time));
        sysLog.setCreateTime(new Date());
        sysLog.setOpResult("成功");
        //保存系统日志
        sysLogService.insert(sysLog);
    }
}
