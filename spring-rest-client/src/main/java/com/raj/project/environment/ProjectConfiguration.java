package com.raj.project.environment;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectConfiguration implements InitializingBean {

	@Autowired
	Properties properties;

	public static Logger logger = Logger.getLogger(ProjectConfiguration.class);
	
	public static String serviceUrl;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		final String currentEnviroment = properties.getProperty("projecct.environment").trim();
		logger.info("Current Environment: "+currentEnviroment);
		if("development".equalsIgnoreCase(currentEnviroment)){
			logger.info("Development Environment Configuration Start\n");
			configDevelopment();
		}
		else if("production".equalsIgnoreCase(currentEnviroment)){
			logger.info("Production Environment Configuration Start\n");
			configProduction();
		}
	}
	
	private void configDevelopment() {
		serviceUrl = properties.getProperty("com.raj.developement").trim();
	}

	private void configProduction() {
		serviceUrl = properties.getProperty("com.raj.production").trim();
	}

}