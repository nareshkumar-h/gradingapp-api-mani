package com.mani.gradingappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.service.AdminService;
import com.mani.gradingappapi.util.Message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EmployeeController {
	@Autowired
	private AdminService adminService;

	@PostMapping("admin/addEmployee")
	//@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Employee API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "SuccessFully Updated", response = Message.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> addEmpolyee(UserDetails userDetails) {
		
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
	
	@PostMapping("admin/employeeSubject")
	//@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Employee API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "SuccessFully Updated", response = Message.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> updateEmpolyeeSubject(@RequestParam("uid")int userId, @RequestParam("sid")int subjectId) {
		
		String errorMessage = null;
		String status = "";
		try {
			adminService.updateEmployeeSubjectService(userId, subjectId );
			status = "Success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}
		
		if (status.equals("Success")) {
			Message message = new Message(status);
			message.setInfoMessage(status);
			return new ResponseEntity<>(message, HttpStatus.CREATED);
			
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
		}
	}
	
}
