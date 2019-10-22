package com.revature.gradingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.gradingapp.validator.EmployeeValidator;
import com.revature.gradingapp.validator.GradeValidator;
import com.revature.gradingapp.validator.StudentValidator;
import com.revature.gradingapp.validator.SubjectValidator;
import com.revature.gradingapp.validator.UserValidator;

@Configuration
public class AppConfig {

	
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
	@Bean
	public EmployeeValidator employeeValidator() {
		return new EmployeeValidator();
	}
}
