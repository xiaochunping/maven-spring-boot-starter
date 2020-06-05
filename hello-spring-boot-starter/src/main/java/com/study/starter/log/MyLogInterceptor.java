package com.study.starter.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class MyLogInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (null != myLog) {
            //  设置开始时间
            long startTime = System.currentTimeMillis();
            startTimeThreadLocal.set(startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (null != myLog) {
            //  获取开始时间
            long startTime = startTimeThreadLocal.get();
            long endTime = System.currentTimeMillis();
            long expendTime = endTime - startTime;

            //  打印参数
            String requestUri = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName() + "#" + method.getName();
            String methodDesc = myLog.desc();
            String parameters = JSON.toJSONString(request.getParameterMap());

            log.info("\n描述：{}\n路径: {}\n方法: {}\n参数：{}\n耗时：{}", methodDesc, requestUri, methodName, parameters, expendTime);
        }
    }
}
