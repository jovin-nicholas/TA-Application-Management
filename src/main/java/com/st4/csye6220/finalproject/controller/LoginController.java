package com.st4.csye6220.finalproject.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.st4.csye6220.finalproject.dao.UserDAO;
import com.st4.csye6220.finalproject.pojo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	private Logger LOG = LoggerFactory.getLogger(LoginController.class);

	private HttpSession session;

	@GetMapping("/login.htm")
	protected String showForm(HttpServletRequest request) {
		session = request.getSession();
		if (session != null && session.getAttribute("user") != null) {
			User loggedUser = (User) session.getAttribute("user");
			return getPageByRole(loggedUser);
		}
		return "login";
	}

	@GetMapping("/logout.htm")
	protected String logout() {
		if (session != null && session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return "redirect:/login.htm";
	}

	@GetMapping("/error.htm")
	protected String showError() {

		return "login_error";
	}

	@PostMapping("/login.htm")
	public ModelAndView handleLogin(@ModelAttribute("user") User user, HttpServletRequest request, UserDAO userDAO)
			throws ServletException, IOException {
		session = request.getSession(false);
		String page;
		LOG.info("Inside handleLogin");

		// checks if user already exists
		if (authenticateUser(user, userDAO)) {
			User loggedUser = getLoggedUser(user, userDAO);
			session.setAttribute("user", loggedUser);
			page = getPageByRole(loggedUser);
		} else {
			page = "redirect:/error.htm";
		}

		return new ModelAndView(page);
	}

	private String getPageByRole(User user) {
		String userRole = user.getRole();

		switch (userRole.trim().toLowerCase()) {
		case "student": {
			return "redirect:/ta/dashboard.htm";
		}
		case "faculty": {
			return "redirect:/faculty/dashboard.htm";
		}
		case "admin": {
			return "redirect:/admin/dashboard.htm";
		}
		default:
			return "login_error";
		}
	}

	private User getLoggedUser(User user, UserDAO userDAO) {
		return (User) userDAO.getUser(user);
	}

	private boolean authenticateUser(User user, UserDAO userDAO) throws ServletException, IOException {
		User loggedUser = userDAO.getUser(user);

		// checks if user exists in the DB
		return loggedUser != null;
	}
}
