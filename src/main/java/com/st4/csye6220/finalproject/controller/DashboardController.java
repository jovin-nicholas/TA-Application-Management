package com.st4.csye6220.finalproject.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.st4.csye6220.finalproject.dao.ApplicationDAO;
import com.st4.csye6220.finalproject.dao.CourseDAO;
import com.st4.csye6220.finalproject.pojo.Application;
import com.st4.csye6220.finalproject.pojo.Course;
import com.st4.csye6220.finalproject.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

//	private Logger LOG = LoggerFactory.getLogger(DashboardController.class);
	final String APPLICATION_STATUS = "Selected";

	@GetMapping("/ta/dashboard.htm")
	public ModelAndView showStudentDashboard(ApplicationDAO applicationDAO, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String page = null;

		if (session == null || session.getAttribute("user") == null) {
			System.out.println("No session found");
			page = "redirect:/login.htm";
		} else {
			System.out.println("Session exists for: " + session.getAttribute("user"));
			page = "application_dashboard";

			User user = (User) session.getAttribute("user");

			List<Application> applicationList = applicationDAO.fetchApplicationsByUser(user);
			mv.addObject("applicationList", applicationList);
			mv.addObject("userRole", user.getRole());
			mv.addObject("userEmail", user.getEmail());
		}
		mv.setViewName(page);
		return mv;
	}

	@PostMapping("/ta/dashboard.htm")
	public ModelAndView redirectApplication() {
		return new ModelAndView("redirect:/ta/apply.htm");
	}

	@GetMapping("/faculty/dashboard.htm")
	public ModelAndView showFacultyDashboard(ApplicationDAO applicationDAO, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String page = null;

		if (session == null || session.getAttribute("user") == null) {
			page = "redirect:/login.htm";
		} else {
			page = "application_dashboard";

			User user = (User) session.getAttribute("user");
			String email = user.getEmail();

			List<Application> applicationList = applicationDAO.fetchApplicationsByProfessorEmail(email);
			System.out.println("Professor List size: " + applicationList.size());
			mv.addObject("applicationList", applicationList);
			mv.addObject("userRole", user.getRole());
			mv.addObject("userEmail", email);
		}
		mv.setViewName(page);
		return mv;
	}

	@PostMapping("/faculty/dashboard.htm")
	public ModelAndView selectDashboard(ApplicationDAO applicationDAO, HttpServletRequest request) {
		String[] selectedValues = request.getParameterValues("selectedApplication");
		if (selectedValues != null) {
			for (int i = 0; i < selectedValues.length; i++) {
				System.out.println("Selected applicant: " + selectedValues[i]);
				long applicationId = Long.parseLong(selectedValues[i]);
				Application application = applicationDAO.getApplication(applicationId);
				application.setStatus(APPLICATION_STATUS);
				application.setReviewDate(new Date());
				applicationDAO.update(application);
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		String email = user.getEmail();

		List<Application> applicationList = applicationDAO.fetchApplicationsByProfessorEmail(email);
		ModelAndView mv = new ModelAndView();
		mv.addObject("applicationList", applicationList);
		mv.addObject("userEmail", user.getEmail());
		mv.setViewName("application_dashboard");
		return mv;
	}

	@GetMapping("/admin/dashboard.htm")
	public ModelAndView showAdminDashboard(CourseDAO courseDAO, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String page = null;

		if (session == null || session.getAttribute("user") == null) {
			page = "redirect:/login.htm";
		} else {
			page = "course_dashboard";

			User user = (User) session.getAttribute("user");
			List<Course> courseList = courseDAO.getCourses();
			mv.addObject("courseList", courseList);
			mv.addObject("userRole", user.getRole());
			mv.addObject("userEmail", user.getEmail());
		}
		mv.setViewName(page);
		return mv;
	}

	@PostMapping(value="/admin/dashboard.htm", params = "submit")
	public ModelAndView addCourse() {
		return new ModelAndView("redirect:/admin/addCourse.htm");
	}
	
	@PostMapping(value="/admin/remove.htm/{courseId}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> removeCourse(@PathVariable long courseId, CourseDAO courseDAO) {
	    System.out.println("Removing course with ID: " + courseId);
	    Map<String, Object> response = new HashMap<>();
	    try {
	        if (courseDAO.removeCourse(courseDAO.getCourse(courseId))) {
	            response.put("success", true);
	            response.put("message", "Course removed successfully.");
	        } else {
	            response.put("success", false);
	            response.put("message", "Course removal failed.");
	        }
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Error: " + e.getMessage());
	    }
	    return response;
	}
	
}
