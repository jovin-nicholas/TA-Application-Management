package com.st4.csye6220.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.st4.csye6220.finalproject.dao.CourseDAO;
import com.st4.csye6220.finalproject.pojo.Course;

@Controller
public class CourseController {

	final String APPLICATION_STATUS = "Pending";

	
	@GetMapping("/admin/addCourse.htm")
	public String ShowCourseForm() {
		System.out.println("Render Course Form");
		return "add_course";
	}

	@PostMapping("/admin/addCourse.htm")
	public ModelAndView AddCourse(@ModelAttribute("course") Course course, CourseDAO courseDAO) {
		System.out.println("Post Course Add Form");
		
		courseDAO.saveCourse(course);
		
		ModelAndView mv = new ModelAndView();
//		mv.addObject("course",course);
		mv.setViewName("redirect:/admin/dashboard.htm");
		return mv;
	}
}
