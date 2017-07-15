package org.security.spring.exp.boot;

import org.security.spring.exp.security.RESTAuthenticationEntryPoint;
import org.security.spring.exp.security.RESTAuthenticationFailureHandler;
import org.security.spring.exp.security.RESTAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Abhideep Bakshi
 *
 *	WebSecurityConfig - Configure the web security and roles access.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier(value = "CustomAuthenticationProvider")
	AuthenticationProvider authProvider;

	@Autowired
	AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
		// auth.inMemoryAuthentication().withUser("Sanjay").password("Sanjay").roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/erp/**").access("hasRole('ROLE_USER')")
		.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		super.configure(http);
	}

}
