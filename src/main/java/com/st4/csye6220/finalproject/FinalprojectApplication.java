package com.st4.csye6220.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SpringBootApplication(scanBasePackages = {"com.st4.csye6220.finalproject.controller", "com.st4.csye6220.finalproject.pojo", "com.st4.csye6220.finalproject.dao", "com.st4.csye6220.finalproject.config"})
public class FinalprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalprojectApplication.class, args);
	}
	
	@GetMapping("/")
	public ModelAndView Home() {
		return new ModelAndView("redirect:/login.htm");
	}
}
