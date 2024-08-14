package com.cric.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * SpringBootJwtApplication class
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = { "com.cric.project.*" })
public class SpringBootJwtApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}

	/**
	 * MessageSource Bean
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages_en");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;

	}

//	@Bean(name = "messageSource")
//	@Profile("test")
//	public MessageSource testMessageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		return messageSource;
//	}

	/**
	 * LocalValidatorFactory Bean
	 */
	@Bean
	public LocalValidatorFactoryBean beanValidatorFactory() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
