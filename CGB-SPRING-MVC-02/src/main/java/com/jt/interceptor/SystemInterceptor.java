package com.jt.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 编写拦截器：SystemInterceptor
 * 自己重写拦截器中的方法并对此拦截进行配置
 * 需求：模拟12306中的一个业务操作，在某个
 * 时间之间允许访问控制层方法，其它时间点都
 * 不允许？
 */
public class SystemInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取当前时间
        long time=System.currentTimeMillis();
        //获取当前日历对象(存储着当前时间信息)
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,14);//起始时间
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long startTime=c.getTimeInMillis();//终止时间
        c.set(Calendar.HOUR_OF_DAY, 23);
        long endTime=c.getTimeInMillis();
        if(time<startTime||time>endTime){
        	System.out.println("本时间点之内不允许访问");
        	//response.sendRedirect(arg0);
        	return false;//表示拦截，不会再去执行控制层方法
        }
		return true;//表示放行，要执行控制层的方法
	}
}
