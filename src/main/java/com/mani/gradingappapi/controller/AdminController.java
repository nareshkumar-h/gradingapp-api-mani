package com.mani.gradingappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	@ApiOperation(value = "Login API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Logged In", response = UserDetails.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	//@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> adminLogin(@RequestParam("username") String name, @RequestParam("password")String password)
	{
		UserDetails userdetail = null;
		String errorMessage = "";
		
		try {
			userdetail = adminService.adminLogin(name,password);
			
		} catch (ServiceException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
			 
		if(userdetail == null){
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		else
		{
			return new ResponseEntity<>(userdetail, HttpStatus.OK);
		}
	}
	
}
