package org.security.spring.exp.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier(value="CustomAuthenticationProvider")
	AuthenticationProvider authProvider;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(authProvider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/","/login.html","/*.html").permitAll()
								.antMatchers("/user/**").access("hasRole('ROLE_USER')").anyRequest()
								.authenticated().and().csrf().disable().formLogin()
								.loginPage("/login.html").usernameParameter("username").passwordParameter("password")
								.failureUrl("/login.html?error=true")
								.defaultSuccessUrl("/user/home.html");
		/*http.authorizeRequests()
	  	.antMatchers("/", "/home").permitAll()
	  	.antMatchers("/admin/**").access("hasRole('ADMIN')")
	  	.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");*/
		
		
		/*http.authorizeRequests().antMatchers("/store/**").access("hasRole('STORE')").anyRequest()
								.authenticated().and().csrf().disable().formLogin()
								.failureUrl("/error")
								.defaultSuccessUrl("/user/home");*/
		
		/*http.authorizeRequests()
						        .anyRequest().authenticated()
						        .and().formLogin().loginPage("/login.jsp").permitAll()
								.failureUrl("/login?error=true")
								.defaultSuccessUrl("/user/home");
		http.authorizeRequests().antMatchers("/store/**").access("hasRole('STORE')").anyRequest()
								.authenticated().and().csrf().disable().formLogin()
								.failureUrl("/error")
								.defaultSuccessUrl("/store/home");*/
								
		
		
		super.configure(http);
	}

	
	
	
}
