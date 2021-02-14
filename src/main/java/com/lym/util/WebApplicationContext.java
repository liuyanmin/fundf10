package com.lym.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class WebApplicationContext implements ApplicationContextAware {
	
	protected static ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext app) throws BeansException {
		if (WebApplicationContext.appContext == null) {
			WebApplicationContext.appContext = app;
		}
	}

	public static ApplicationContext getAppContext() {
		return appContext;
	}
	
	public static Object getBean(String beanName) {
		return getAppContext().getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz) {
		return getAppContext().getBean(clazz);
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		return getAppContext().getBean(name, clazz);
	}

}
