package com.panda.util;

import org.aspectj.lang.JoinPoint;

public class ValidationAspect {
    public void beforMethod(){
    	System.out.println("开始调用controller方法");
    }

    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("调用controller方法: "+methodName+" 结束之后.");
    }
    //有返回值时调用
    public void afterReturnMethod(){
    	System.out.println("调用方法结束之后。。。。");
    }
    //抛出异常时才调用  
    public void afterThrowingMethod()  
    {  
        System.out.println("校验token出现异常了......");  
    }

}