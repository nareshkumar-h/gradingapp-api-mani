package com.mani.gradingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.validator.GradeValidator;

@RestController
public class ScoreController {
	@Autowired
	private GradeValidator gradeValidator;
	@Autowired
	private AdminService adminservice;
	
	@GetMapping("defineScore")
	public String defineScore(@RequestParam("grade") String grade, @RequestParam("min") int min, @RequestParam("max") int max)
	{
		String errorMessage = null;
		String status = "";
		try {
			gradeValidator.isGradeExist(grade.toUpperCase(), min, max);

			adminservice.updateScoreRangeService(grade.toUpperCase(), min, max);

			status = "Success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		} catch (ValidatorException e) {
			errorMessage = e.getMessage();
		}catch (DBException e) {
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
	
	@GetMapping("viewScore")
	public String viewScore()
	{
		List<ScoreRange> list = null;
		String errorMessage = null;
		String status = "";
		try {
			list = adminservice.viewScoreRangeService();
			status = "success";

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}

		String json = null;
		if (status.equals("success")) {
			// convert list to json
			Gson gson = new Gson();
			json = gson.toJson(list);
		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("errMessage", errorMessage);
			json = obj.toString();
		}
		
		return json;
	}
}
