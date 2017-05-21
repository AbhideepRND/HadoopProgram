package org.security.spring.exp.boot;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class ApplicationContext {

	@Value("${db.driver}")
	private String dbDriverName;
	
	@Value("${db.url}")
	private String dbConnectionUrl;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;
	
	@Value("${queryPropertyUrl}")
	private String queryUrl;
	
	@Bean
	public DataSource dataSource(){
		final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(dbDriverName);
		driverManagerDataSource.setUrl(dbConnectionUrl);
		driverManagerDataSource.setUsername(dbUsername);
		driverManagerDataSource.setPassword(dbPassword);
		return driverManagerDataSource;
	}
	
	@Bean(name={"sqlProperties"}) 
	public Properties sqlProperties() throws IOException{
		final Properties properties = new Properties();
		final ClassLoader classLoader = getClass().getClassLoader();
		final File file = new File(classLoader.getResource(queryUrl).getFile());
		final FileInputStream fileInputStream = new FileInputStream(file);
		properties.load(fileInputStream);
		return properties;
	}
}
