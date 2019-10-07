package com.mani.gradingappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.service.UserService;

@RestController
public class EmployeeController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService UserService;

	@GetMapping("addEmployee")
	public String addEmpolyee(@RequestParam("name")String name, @RequestParam("email")String email, @RequestParam("mobno")Long mobNo, @RequestParam("password")String password, @RequestParam("role")String role, @RequestParam("subject")String subject) {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setName(name);
		userDetails.setEmail(email);
		userDetails.setMobno(mobNo);
		userDetails.setPassword(password);
		userDetails.setRole(role);
		userDetails.setSubject(subject);
		
		String errorMessage = null;
		String status = "";
		try {
			adminService.addEmployeeService(userDetails);
			status = "Success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}

		String json = null;
		// Gson gson = new Gson();
		if (status.equals("Success")) {

			JsonObject obj = new JsonObject();
			obj.addProperty("responseMessage", "success");
			json = obj.toString();

		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("responseMessage", errorMessage);
			json = obj.toString();
		}
	return json;	
	}
	
	@GetMapping("updateEmployee")
	public String updateEmpolyee(@RequestParam("name")String name, @RequestParam("email")String email, @RequestParam("mobno")Long mobNo, @RequestParam("password")String password, @RequestParam("role")String role, @RequestParam("subject")String subject) {

		UserDetails userDetails = new UserDetails();
		userDetails.setName(name);
		userDetails.setEmail(email);
		userDetails.setMobno(mobNo);
		userDetails.setPassword(password);
		userDetails.setRole(role);
		userDetails.setSubject(subject);
		
		String errorMessage = null;
		String status = "";
		try {
			UserService.updateEmployeeService(userDetails);
			status = "Success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}
	
		String json = null;
		// Gson gson = new Gson();
		if (status.equals("Success")) {
	
			JsonObject obj = new JsonObject();
			obj.addProperty("responseMessage", "success");
			json = obj.toString();
	
		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("responseMessage", errorMessage);
			json = obj.toString();
		}
	
	return json;
	}
}
