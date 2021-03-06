package com.thankjava.toolkit3d.aop.core;

import java.lang.reflect.Method;

import com.thankjava.toolkit.reflect.ReflectHelper;
import com.thankjava.toolkit3d.aop.anno.After;
import com.thankjava.toolkit3d.aop.anno.Before;
import com.thankjava.toolkit3d.aop.entity.AopConfig;
import com.thankjava.toolkit3d.aop.entity.AopParam;


class InvokeBreankMethod {


	public AopParam before(AopConfig aopConfig, Before before, AopParam param){
		Object aopInstance = aopConfig.getAopBeforeInstance();
		Method cutMethod = ReflectHelper.getMethod(aopInstance, before.cutMethod(), AopParam.class);
		param = (AopParam) ReflectHelper.invokeMethod(aopInstance, cutMethod, param);
		return param;
	}
	

	public AopParam after(AopConfig aopConfig, After after, AopParam param, Object invokeResult){
		Object aopInstance = aopConfig.getAopAfterInstance();
		Method cutMethod = ReflectHelper.getMethod(aopInstance, after.cutMethod(), AopParam.class);
		return (AopParam) ReflectHelper.invokeMethod(aopInstance, cutMethod, param);
	}
}
