package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.annotation.TimeMonitor;

@Controller
@RequestMapping("/")
public class InterceptorController {
	
	@RequestMapping("doSomeThing")
	@ResponseBody
	@TimeMonitor
	public String doSomeThing(){
		//long startTime=System.nanoTime();
		String result="do some thing";
		System.out.println(result);
		try{Thread.sleep(500);}catch(Exception e){}
		//long endTime=System.nanoTime();
		//System.out.println(endTime-startTime);
		return result;
	}
	/**
	 * 要求此方法在某个时间点之间可以方法
	 * 其它时间不可以访问？如何实现
	 * @return
	 */
	@RequestMapping("doStudyMVC")
	@ResponseBody
	public String doStudyMVC(){
		String result="do Study MVC";
		System.out.println(result);
		try{Thread.sleep(500);}catch(Exception e){}
		return result;
	}
	
	@RequestMapping("doStudyInterceptor")
	@ResponseBody
	public String doStudyInterceptor(){
		//long startTime=System.nanoTime();
		String result="do study interceptor";
		System.out.println(result);
		//long endTime=System.nanoTime();
		//System.out.println(endTime-startTime);
		return result;
	}
}







