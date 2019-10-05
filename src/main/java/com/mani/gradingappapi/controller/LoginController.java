package com.mani.gradingappapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.UserValidator;

@RestController
public class LoginController {

	private final UserValidator uservalidator = new UserValidator();
	private final AdminService adminService = new AdminService();
	
	@GetMapping("adminlogin")
	public String adminLogin(@RequestParam("username") String name, @RequestParam("password") String password) {

		UserDetails userdetail = null;
		String errMessage = "";
		
		try {
			uservalidator.userInput(name, password);
			userdetail = adminService.adminLogin(name, password);
		} catch (ValidatorException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		} catch (ServiceException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		}
		
		String json = null;
		Gson gson = new Gson();
		if(userdetail != null)
		{
			json = gson.toJson(userdetail);
		} else if(userdetail == null){
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("errorMessage", errMessage);
			json = jsonObj.toString();
		}
		return json;
	}
	
	@GetMapping("userlogin")
	public String userLogin(@RequestParam("username") String name, @RequestParam("password")String password)
	{
		UserService userservice = new UserService();

		UserDetails userdetail = null;
		String errMessage = "";
		
		try {
			uservalidator.userInput(name, password);
			userdetail = userservice.userLogin(name, password);
		} catch (ValidatorException e) {
			errMessage = e.getMessage();
		} catch (ServiceException e) {
			errMessage = e.getMessage();
		}
		//prepare JSON obj
		String json = null;
		Gson gson = new Gson();
		if(userdetail != null)
		{
			json = gson.toJson(userdetail);
		} else if(userdetail == null){
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("errorMessage", errMessage);
			json = jsonObj.toString();
		}
		return json;	
	}
}
