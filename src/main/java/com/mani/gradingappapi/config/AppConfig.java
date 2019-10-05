package com.mani.gradingappapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.service.UserFeatureService;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.GradeValidator;
import com.revature.gradingsystem.validator.StudentValidator;
import com.revature.gradingsystem.validator.SubjectValidator;
import com.revature.gradingsystem.validator.UserValidator;

@Configuration
public class AppConfig {

	@Bean
	public AdminService adminService() {
		return new AdminService();
	}
	@Bean
	public UserFeatureService userFeature() {
		return new UserFeatureService();
	}
	@Bean
	public UserService userService() {
		return new UserService();
	}
	
	@Bean
	public UserValidator userValidator() {
		return new UserValidator();
	}
	
	@Bean
	public GradeValidator gradeValidator() {
		return new GradeValidator();
	}
	@Bean
	public SubjectValidator subjectValidator() {
		return new SubjectValidator();
	}
	@Bean
	public StudentValidator studentValidator() {
		return new StudentValidator();
	}
}
