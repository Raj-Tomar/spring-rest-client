package com.raj.app.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.raj.util.WebServiceUtil;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.raj.project.environment", 
								"com.raj.employee.controller",
								"com.raj.employee.serviceImpl"
								})
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    Environment environment;

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
	/*
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {
		return new CommonsMultipartResolver();
	}*/
	 

	// Locale Configuration Start

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:messages");
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		/*
		 CookieLocaleResolver resolver = new CookieLocaleResolver();
		 resolver.setDefaultLocale(new Locale("en"));
		 resolver.setCookieName("myLocaleCookie");
		 resolver.setCookieMaxAge(4800); return resolver;
		 */ 
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
	}
	// Locale Configuration End
	
	// Properties file configuration
	@Bean(name = "properties")
	public PropertiesFactoryBean mapper() {
	    PropertiesFactoryBean bean = new PropertiesFactoryBean();
	    bean.setLocation(new ClassPathResource("spring-rest-client.properties"));
	    return bean;
	}
	
	
	// WebService Utility Class Bean
	@Bean(name = "webService")
	public WebServiceUtil webServiceUtil() {
		return new WebServiceUtil();
	}
	
}
