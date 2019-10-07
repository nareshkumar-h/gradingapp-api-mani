package com.mani.gradingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.validator.StudentValidator;

@RestController
public class ResultController {
	@Autowired
	private UserService userService;

	@GetMapping("studentResult")
	public String studentResult(@RequestParam("regno")int regno){
		List<StudentMark> markList = null;
		StudentGradeDTO studentResult = null;
		JsonObject obj = new JsonObject();
		String json ="";
		
		try {
			
			// Reg-No validator
			StudentValidator studentValidate = new StudentValidator();
			studentValidate.isRegnoExistService(regno);
			
			//get the StudentName, Average, Grade
			studentResult = userService.getStudentResult(regno);
			
			//get the Marks and Sub-Code
			markList = userService.getStudentMarks(regno);
			
			Gson gson = new Gson();
			String json1 = gson.toJson(markList);
			
			String json2 = gson.toJson(studentResult);
			
			json = "{\"marks\"" +":"+ "" + json1+ ", \"SD\""+":" + "" + json2+ "}"; 
			//convert list to json
			
		} catch (Exception e) {
			obj.addProperty("errMsg",e.getMessage());
			json = obj.toString();
		}
	return json;	
	}
}
