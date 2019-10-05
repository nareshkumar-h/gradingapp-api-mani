package com.mani.gradingappapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.validator.GradeValidator;

@RestController
public class DefineScoreController {
	
	private final GradeValidator gradeValidator = new GradeValidator();
	private final AdminService adminservice = new AdminService();
	
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
}
