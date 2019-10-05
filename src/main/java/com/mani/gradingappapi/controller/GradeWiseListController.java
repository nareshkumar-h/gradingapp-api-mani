package com.mani.gradingappapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.service.UserFeatureService;

@RestController
public class GradeWiseListController {

	@GetMapping("gradeWiseList")
	public String defineScore() {

		List<StudentGradeDTO> list = null;
		String errorMessage = "";
		String status = "";

		try {
			list = new UserFeatureService().listOfStudentService();
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
