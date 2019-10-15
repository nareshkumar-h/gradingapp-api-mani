package com.mani.gradingappapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.StudentMark;
import com.mani.gradingappapi.model.Subject;
import com.mani.gradingappapi.service.UserService;
import com.mani.gradingappapi.util.Message;
import com.mani.gradingappapi.validator.StudentValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MarkController {
	@Autowired
	private StudentValidator studentValidator;
	@Autowired
	private UserService userService;
	
	@PutMapping("updateMark")
	//@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Mark API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully updated mark", response = Message.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> updateMark(@RequestParam("regno")int regno, @RequestParam("mark1")int mark1, @RequestParam("mark2")int mark2, @RequestParam("mark3")int mark3, @RequestParam("mark4")int mark4, @RequestParam("mark5")int mark5) {
		
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
			studentValidator.isRegnoUpdated(regno);

			userService.updateMarksAndGradeService(regno, list);

			status = "Success";
		} catch (ValidatorException e) {
			errorMessage = e.getMessage();

		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}

		if (status.equals("Success")) {
			Message message = new Message(status);
			return new ResponseEntity<>(message, HttpStatus.OK);
			
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
		}	
	}
}
