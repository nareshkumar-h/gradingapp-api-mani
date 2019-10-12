package com.mani.gradingappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.util.Message;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.service.UserService;
import com.mani.gradingappapi.validator.UserValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {
	@Autowired
	private UserValidator uservalidator;
	@Autowired
	private UserService userservice;
	
	@PostMapping("userlogin")
	@ApiOperation(value = "Login API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Logged In", response = UserDetails.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	//@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> userLogin(@RequestParam("username") String name, @RequestParam("password")String password)
	{
		UserDetails userdetail = null;
		String errMessage = "";
		
		try {
				uservalidator.userInput(name, password);
				userdetail = userservice.userLogin(name,password);
			
		} catch (ValidatorException e) {
			errMessage = e.getMessage();
		}catch (ServiceException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		}
			 
		if(userdetail == null){
			Message message = new Message(errMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		else
		{
			return new ResponseEntity<>(userdetail, HttpStatus.OK);
		}
		
			
	}

}
