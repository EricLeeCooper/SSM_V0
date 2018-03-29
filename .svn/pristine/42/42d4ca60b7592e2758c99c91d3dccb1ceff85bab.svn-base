package com.panda.util;

import org.aspectj.lang.JoinPoint;

@SuppressWarnings("unused")
public class LogHandler {
	public void beforMethod(){
    	System.out.println("开始调用controller方法");
    }
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("调用controller方法: "+methodName+" 结束之后.");
    }
	private void beforeAct() {
		System.out.println("调用方法开始前记录...");
	}
	private void afterAct() {
		System.out.println("调用方法结束后记录...");
	}
}
