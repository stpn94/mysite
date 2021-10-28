package com.douzone.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageConfig {

//<!-- Message Source -->		
//<!-- MessageConfig.java로 간다. -->
//<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
//		<property name="basenames">
//			<list>
//				<value>messages/messages_ko</value>
//			</list>
//		</property>
//</bean>		
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("com/douzone/mysite/config/web/messages_ko");
		messageSource.setDefaultEncoding("utf-8");
		
		return messageSource;
	}
	
}
	