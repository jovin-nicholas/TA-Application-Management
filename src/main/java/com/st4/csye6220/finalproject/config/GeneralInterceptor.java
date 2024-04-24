package com.st4.csye6220.finalproject.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.st4.csye6220.finalproject.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class GeneralInterceptor implements HandlerInterceptor {
	private Logger LOG = LoggerFactory.getLogger(GeneralInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.info("preHandle Interceptor called... " + request.getRequestURI(), request.getMethod());

		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("user") != null) {
			User loggedUser = (User) session.getAttribute("user");
			LOG.info("preHandle Interceptor Session found... " + loggedUser.getEmail());
			return checkAccess(request, response, loggedUser);
		} else {
			LOG.info("preHandle Interceptor Session NOT found... ");
			response.sendRedirect(request.getContextPath() + "/login.htm");
			return false;
		}
	}

	private boolean checkAccess(HttpServletRequest request, HttpServletResponse response, User user)
			throws IOException {
		String userRole = user.getRole();
		LOG.info("preHandle Interceptor logged user role... " + userRole);

		if (userRole.equals("admin") && request.getRequestURI().startsWith("/admin/")) {
			return true;
		} else if (userRole.equals("faculty") && request.getRequestURI().startsWith("/faculty/")) {
			return true;
		} else if (userRole.equals("student") && request.getRequestURI().startsWith("/ta/")) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/error.htm");
			return false;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null) {
			LOG.error("exception caught in posthandle Interceptor called... " + ex.getMessage());
		}
		LOG.info("afterCompletion Interceptor called... " + request.getRequestURI(), request.getMethod());
	}

}
