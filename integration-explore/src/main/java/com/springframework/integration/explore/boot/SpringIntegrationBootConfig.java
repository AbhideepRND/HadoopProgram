package com.springframework.integration.explore.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@SpringBootApplication
@ImportResource("classpath:integration-config.xml")
public class SpringIntegrationBootConfig extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringIntegrationBootConfig.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationBootConfig.class, args);
	}
}
