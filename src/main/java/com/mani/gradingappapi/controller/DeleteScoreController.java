package com.mani.gradingappapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.service.AdminService;

@RestController
public class DeleteScoreController {

	private final AdminService adminservice = new AdminService();
	@GetMapping("deleteScore")
	public String deleteScore(){
		
		String status = "";
		try {
			adminservice.deleteScoreRangeService();
			status = "Score Range Deleted..";
		} catch (DBException e) {
			status= e.getMessage();
		}
	
			JsonObject obj = new JsonObject();
			obj.addProperty("message", status);
			String json = obj.toString();
			
		return json;
	}
}
