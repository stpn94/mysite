package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 타겟 = "이 어노테이션은 어디에다가 붙여햐 한다" 정의 ex) @Target(ElementType.PARAMETER)
// 리텐션 = 기간(언제)?						  ex) @Retention(RetentionPolicy.RUNTIME)
@Target( value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	// @Auth("require")
	public boolean test() default false;
	
	// 생략가능
	// @Auth == 그냥 이렇게 하면 USER만 접속가능
	// public String value() default "USER";
	
	// 생략가능
	// @Auth("ADMIN") = 어드민만 접속가능
	// @Auth("USER")  = 유저민만 접속가능
	public String role() default "USER";
}
