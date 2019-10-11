package com.mani.gradingappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.util.Message;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EmployeeController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService UserService;

	@PostMapping("addEmployee")
	//@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Employee API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "SuccessFully Updated", response = Message.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> addEmpolyee(@RequestParam("name")String name, @RequestParam("email")String email, @RequestParam("mobno")Long mobNo, @RequestParam("password")String password, @RequestParam("role")String role, @RequestParam("subject")String subject) {
		
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
		
		if (status.equals("Success")) {
			Message message = new Message(status);
			return new ResponseEntity<>(message, HttpStatus.CREATED);
			
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PutMapping("updateEmployee")
	//@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Employee API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Logged In", response = Message.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> updateEmpolyee(@RequestParam("name")String name, @RequestParam("email")String email, @RequestParam("mobno")Long mobNo, @RequestParam("password")String password, @RequestParam("role")String role, @RequestParam("subject")String subject) {

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
	
		if (status.equals("Success")) {
			Message message = new Message(status);
			return new ResponseEntity<>(message, HttpStatus.OK);
			
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
		}
	}
}
