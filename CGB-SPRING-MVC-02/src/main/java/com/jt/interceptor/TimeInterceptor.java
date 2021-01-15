package com.jt.interceptor;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jt.annotation.TimeMonitor;

/**
 * 拦截器的编写：
 * 1)实现HandlerInterceptor接口
 * 2)继承HandlerInterceptorAdapter类
 * 拦截器的配置
 * 1)基于xml方式(spring-configs.xml)
 * 2)基于Annotation方式(注解配置类中)
 */
public class TimeInterceptor implements HandlerInterceptor {
	public TimeInterceptor() {
		System.out.println("TimeInterceptor()");
	}
	/**控制层方法执行之前执行*/
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle()");
		//对参数进行向下转型(目的是要获取我们要执行的方法对象)
		HandlerMethod hMethod=(HandlerMethod)handler;
		//获取目标方法对象上的注解
		TimeMonitor tMointor=
		hMethod.getMethodAnnotation(TimeMonitor.class);
		if(tMointor!=null){
		long startTime=System.currentTimeMillis();
		request.setAttribute("startTime",startTime);
		}
		return true;//false 表示拦截，true表示放行
	}
	/**控制层方法执行结束之后执行*/
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	    System.out.println("postHandle()");
	}
	/**视图渲染结束以后执行*/
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,
			Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion()");
		//将handler向下转型(目的获取方法相关信息)
		HandlerMethod hMethod=(HandlerMethod)handler;
		//获得方法上的注解
		TimeMonitor tMonitor=
		hMethod.getMethodAnnotation(TimeMonitor.class);
		if(tMonitor!=null){//说明要进行时间监控，计算方法执行时长。
		long endTime=System.currentTimeMillis();
		long startTime=(Long)request.getAttribute("startTime");
		long totalTime=endTime-startTime;
		System.out.println(handler.getClass().getName());
		//获取要执行的方法所在的类的信息
		String beanCls=hMethod.getBeanType().getName();
		//获取要执行的方法对象
		Method method=hMethod.getMethod();
		System.out.println(beanCls+"."+method.getName()+"的执行总时长:"+totalTime);
		}
	}
}
