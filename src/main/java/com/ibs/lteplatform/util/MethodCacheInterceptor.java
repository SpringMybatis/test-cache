package com.ibs.lteplatform.util;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;

public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();

		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Element element = this.cache.get(cacheKey);
		if (element == null) {
			Object result = invocation.proceed();
			element = new Element(cacheKey, (Serializable) result);
			if (whetherCache(arguments)) {
				this.cache.put(element);
			}
		}
		return element.getValue();
	}

	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (Object object1 : arguments) {
				if ((object1 instanceof Object[])) {
					Object[] obj = (Object[]) object1;
					for (Object object2 : obj)
						sb.append(".").append(object2);
				} else {
					sb.append(".").append(object1);
				}
			}
		}

		return sb.toString();
	}

	public boolean whetherCache(Object[] arguments) {
		if ((arguments.length > 0) && ((arguments[0] instanceof Boolean))) {
			return ((Boolean) arguments[0]).booleanValue();
		}

		return true;
	}

	public void afterPropertiesSet() throws Exception {
		if (this.cache == null)
			throw new IllegalArgumentException("Cache should not be null.");
	}
}
