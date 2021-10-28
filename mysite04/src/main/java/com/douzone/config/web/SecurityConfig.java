package com.douzone.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.mysite.security.AuthInterceptor;
import com.douzone.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.mysite.security.LoginInterceptor;
import com.douzone.mysite.security.LogoutInterceptor;

public class SecurityConfig extends WebMvcConfigurerAdapter {
	// Argument Resolver
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(handlerMethodArgumentResolver());
	}
	
	//////////////////////////////

	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
		//		<mvc:interceptor>
		//		<mvc:mapping path="/user/auth"/>
		//		<bean class="com.douzone.mysite.security.LoginInterceptor" />
		//		</mvc:interceptor>
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();

		//		<mvc:interceptor>
		//		<mvc:mapping path="/user/logout"/>
		//		<bean class="com.douzone.mysite.security.LogoutInterceptor" />
		//		</mvc:interceptor>

	}

	@Bean
	public HandlerInterceptor authIntercepter() {
		return new AuthInterceptor();

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/auth");

		registry.addInterceptor(logoutInterceptor()).addPathPatterns("/user/logout");

		registry.addInterceptor(authIntercepter())
		.addPathPatterns("/*")
		.excludePathPatterns("/user/auth")
		.excludePathPatterns("/user/logout")
		.excludePathPatterns("/assets/*");

		//		<mvc:interceptor>
		//		<mvc:mapping path="/**"/>
		//		<mvc:exclude-mapping path="/user/auth"/>
		//		<mvc:exclude-mapping path="/user/logout"/>
		//		<mvc:exclude-mapping path="/assets/**"/>
		//		<bean class="com.douzone.mysite.security.AuthInterceptor" />
		//		</mvc:interceptor>
	}
}
