package com.st4.csye6220.finalproject.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.st4.csye6220.finalproject.dao.UserDAO;
import com.st4.csye6220.finalproject.pojo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {

	private Logger LOG = LoggerFactory.getLogger(RegisterController.class);
	private HttpSession session;

	@GetMapping("/register.htm")
	protected String showForm(HttpServletRequest request) {
		session = request.getSession();
		if (session != null && session.getAttribute("user") != null) {
            User loggedUser = (User) session.getAttribute("user");
			return getPageByRole(loggedUser);
		}
		return "register";
	}
	

	@PostMapping("/register.htm")
	public String handleRegistration(@ModelAttribute("user") User user, HttpServletRequest request, UserDAO userDAO)
			throws ServletException, IOException {
		session = request.getSession(false);
		String page;
		LOG.info("Inside handleRegistration");
		if (authenticateUser(user, userDAO)) {
			userDAO.saveUser(user);
			session.setAttribute("user", user);
			page = getPageByRole(user);
		} else {
			page = "login_error";
		}

		return page;
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
				return "redirect:/error.htm";
		}
	}
	
	private boolean authenticateUser(User user, UserDAO userDAO) throws ServletException, IOException {
		User loggedUser = userDAO.getUserByEmail(user);

		// checks if user exists in the DB
		return loggedUser == null;
	}

}
