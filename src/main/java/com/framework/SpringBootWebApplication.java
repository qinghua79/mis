package com.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(SpringBootWebApplication.class);
	    }

}
