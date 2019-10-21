package com.mani.gradingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.util.Message;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/login")
	@ApiOperation(value = "Login API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Logged In", response = UserDetails.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	//@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> userLogin(@RequestParam("username") String name, @RequestParam("password")String password)
	{
		UserDetails userdetail = null;
		String errorMessage = "";
		
		try {
			userdetail = userservice.userLogin(name,password);
			
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
	
	@GetMapping("/list")
	@ApiOperation(value = "Login API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Logged In", response = List.class)})
	//@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> listOfUser()
	{
		List<UserDetails> userList = null;
		
		userList = userservice.listOfUser();
			 
		return new ResponseEntity<>(userList, HttpStatus.OK);
			
	}

}
