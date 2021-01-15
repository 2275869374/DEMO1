package com.jt.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//定义注解何时有效
@Target(ElementType.METHOD)//target用于描述注解修饰哪些元素
public @interface TimeMonitor {
	//......
}

