package com.mani.gradingappapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.model.Subject;
import com.revature.gradingsystem.service.UserFeatureService;
import com.revature.gradingsystem.validator.StudentValidator;

@RestController
public class UpdateMarkController {

	@GetMapping("updateMark")
	public String updateMark(@RequestParam("regno")int regno, @RequestParam("mark1")int mark1, @RequestParam("mark2")int mark2, @RequestParam("mark3")int mark3, @RequestParam("mark4")int mark4, @RequestParam("mark5")int mark5) {
		
		StudentMark sm1 = new StudentMark();

		sm1.setMark(mark1);
		Subject subject1 = new Subject();
		subject1.setCode("ENG11");
		sm1.setSubject(subject1);

		StudentMark sm2 = new StudentMark();

		sm2.setMark(mark2);
		Subject subject2 = new Subject();
		subject2.setCode("MAT12");
		sm2.setSubject(subject2);

		StudentMark sm3 = new StudentMark();

		sm3.setMark(mark3);
		Subject subject3 = new Subject();
		subject3.setCode("PHY13");
		sm3.setSubject(subject3);

		StudentMark sm4 = new StudentMark();

		sm4.setMark(mark4);
		Subject subject4 = new Subject();
		subject4.setCode("CHE14");
		sm4.setSubject(subject4);

		StudentMark sm5 = new StudentMark();

		sm5.setMark(mark5);
		Subject subject5 = new Subject();
		subject5.setCode("COM15");
		sm5.setSubject(subject5);

		List<StudentMark> list = new ArrayList<StudentMark>();
		list.add(sm1);
		list.add(sm2);
		list.add(sm3);
		list.add(sm4);
		list.add(sm5);
		
		String errorMessage = null;
		String status = "";
		try {
			StudentValidator studentValidate = new StudentValidator();
			studentValidate.isRegnoUpdated(regno);

			new UserFeatureService().updateMarksAndGradeService(regno, list);

			status = "Success";
		} catch (ValidatorException e) {
			errorMessage = e.getMessage();

		} catch (ServiceException e) {
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
