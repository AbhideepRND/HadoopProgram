package org.security.spring.exp.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Abhideep Bakshi
 *
 *	AccessDeniedHandlerImpl - Custom access denied handle
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest arg0, HttpServletResponse arg1, AccessDeniedException arg2)
			throws IOException, ServletException {
		arg1.sendRedirect("/accessDenied.html");
	}

}
