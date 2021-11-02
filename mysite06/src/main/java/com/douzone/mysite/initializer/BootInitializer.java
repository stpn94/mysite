package com.douzone.mysite.initializer;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.douzone.mysite.MySiteApplication;

public class BootInitializer extends SpringBootServletInitializer {
	/* war에서 발동 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MySiteApplication.class);
	}

}
