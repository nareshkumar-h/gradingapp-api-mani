package com.mani.gradingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.service.UserService;

@RestController
public class SubjectController {
	@Autowired
	private UserService userFeature;
	
	@GetMapping("subjectWise")
	public String subjectWiseRankHolder(@RequestParam("subjectCode")String subCode){
		
		List<StudentMark> list = null;
		String errorMessage = "";
		String status = "";
		try {
			list = userFeature.findBySubjectCodeService(subCode);
			status = "success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}

		// convert list to json
		String json = "";
		if (status.equals("success")) {
			// convert list to json
			Gson gson = new Gson();
			json = gson.toJson(list);
			System.out.println(list);
		} else {
			JsonObject obj = new JsonObject();
			obj.addProperty("errMessage", errorMessage);
			json = obj.toString();
		}
	return json;	
	}
}
