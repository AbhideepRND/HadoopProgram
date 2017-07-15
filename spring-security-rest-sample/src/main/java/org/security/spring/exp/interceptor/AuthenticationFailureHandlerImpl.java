package org.security.spring.exp.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author Abhideep Bakshi
 *
 *	AuthenticationFailureHandlerImpl - Custom Auth failure handler
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse arg1,
			AuthenticationException arg2) throws IOException, ServletException {
		arg0.getSession().setAttribute("errorMsg", arg2.getMessage());
		arg1.sendRedirect("/authfail.html");
	}

}
