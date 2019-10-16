package com.mani.gradingappapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mani.gradingappapi.validator.EmployeeValidator;
import com.mani.gradingappapi.validator.GradeValidator;
import com.mani.gradingappapi.validator.StudentValidator;
import com.mani.gradingappapi.validator.SubjectValidator;
import com.mani.gradingappapi.validator.UserValidator;

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
