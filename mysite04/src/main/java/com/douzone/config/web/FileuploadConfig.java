package com.douzone.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class FileuploadConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	// Multipart Resolver
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize", Long.class));            //최대업로드 가능한 바이트크기 
		multipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize", Integer.class));     //디스크에 임시 파일을 생성하기 전에 메모리에 보관할수있는 최대 바이트 크기
		multipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));                    //defaultEncoding
		
		return multipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
			.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
	}
}
