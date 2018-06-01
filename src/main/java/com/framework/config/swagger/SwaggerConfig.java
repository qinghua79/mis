package com.framework.config.swagger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableWebMvc
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		  return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                //.ignoredParameterTypes(HttpSession.class, Authentication.class, HttpServletRequest.class, HttpServletResponse.class)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.framework"))
	                .paths(PathSelectors.any())
	                .build();
	}
	private ApiInfo apiInfo() {
	      return new ApiInfoBuilder()
	                .title("framework 1.0 api")
	                .description(" 企业后台管理基础框架")
	                .termsOfServiceUrl("http://www.frame.me/")
	                .license("apache 2.0")
	                .version("3.0")
	                .build();
   }
}
