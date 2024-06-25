package com.spring.ex001.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.spring.ex001.intercepter.Loginintercepter;

@Configuration
@ComponentScan(basePackages="com.spring.ex001")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 인터셉터 추가
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Loginintercepter()) // 인터셉터 객체
					.order(1)	// 적용순서 (낮을수록 우선순위)
					.addPathPatterns("/**")	// 적용할 url 패턴
					.excludePathPatterns("/login/login", "/loginAction","/resources/**", "/book*");	// 제외할 url 패턴
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
