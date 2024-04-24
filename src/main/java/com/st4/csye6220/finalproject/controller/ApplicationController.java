package com.st4.csye6220.finalproject.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.st4.csye6220.finalproject.dao.ApplicationDAO;
import com.st4.csye6220.finalproject.dao.CourseDAO;
import com.st4.csye6220.finalproject.dao.UserDAO;
import com.st4.csye6220.finalproject.pojo.Application;
import com.st4.csye6220.finalproject.pojo.Course;
import com.st4.csye6220.finalproject.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ApplicationController {

	final String APPLICATION_STATUS = "Pending";

	@GetMapping("/ta/apply.htm")
	public ModelAndView showForm(UserDAO userDAO, CourseDAO courseDAO, HttpSession session) {
		if (session == null || session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/login.htm");
		} else {
			List<Course> courseList = courseDAO.getCourses();
			User user = (User) session.getAttribute("user");
			ModelAndView mv = new ModelAndView("ta_application_form");
			mv.addObject("userEmail", user.getEmail());
			mv.addObject("userId", user.getUserId());
			mv.addObject("courseList", courseList);
			return mv;
		}
	}

	@PostMapping("/ta/apply.htm")
//  public void handleApplication(@RequestParam("resume") MultipartFile resume, HttpServletRequest request, ApplicationDAO applicationDAO) {
	public ModelAndView handleApplication(HttpServletRequest request, ApplicationDAO applicationDAO, UserDAO userDAO,
			CourseDAO courseDAO) {

		User user = userDAO.getUserByEmail(request.getParameter("applicantEmail"));
		Course course = courseDAO.getCourse(Long.parseLong(request.getParameter("applicationCourseId")));

		Application application = new Application();
		application.setUser(user);
		application.setStatus(APPLICATION_STATUS);
		application.setProfessorEmail(request.getParameter("professorEmail"));
		application.setSubmissionDate(new Date());
		application.setCourse(course);

		System.out.println("User ID: " + user.getUserId() + ", " + application.getApplicationId());
		applicationDAO.saveApplication(application);
		return new ModelAndView("redirect:/ta/dashboard.htm");
	}
	
	@GetMapping(value="/ta/checkApplication.htm", produces = "application/json")
	@ResponseBody
	public Map<String, Object> checkApplication(long userId, long courseId, ApplicationDAO applicationDAO) {
	    System.out.println("Check application for ID: " + userId + courseId);
	    Map<String, Object> response = new HashMap<>();
	    try {
	        if (applicationDAO.checkDuplicate(userId, courseId)) {
	            response.put("success", true);
	            response.put("message", "Application submitted successfully.");
	        } else {
	            response.put("success", false);
	            response.put("message", "You have already submitted an application for this course.");
	        }
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Error: " + e.getMessage());
	    }
	    return response;
	}
}
