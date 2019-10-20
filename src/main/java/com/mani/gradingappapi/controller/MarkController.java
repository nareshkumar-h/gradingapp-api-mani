package com.mani.gradingappapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.dto.UpdateMarkDTO;
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
	
	//@PutMapping("updateMark")
	@PostMapping("updateMark")
	//@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Mark API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully updated mark", response = Message.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> updateMark(UpdateMarkDTO updateMark) {
		System.out.println(updateMark);
		
		StudentMark sm1 = new StudentMark();

		sm1.setMark(updateMark.getMark1());
		Subject subject1 = new Subject();
		subject1.setCode("ENG11");
		sm1.setSubject(subject1);

		StudentMark sm2 = new StudentMark();

		sm2.setMark(updateMark.getMark2());
		Subject subject2 = new Subject();
		subject2.setCode("MAT12");
		sm2.setSubject(subject2);

		StudentMark sm3 = new StudentMark();

		sm3.setMark(updateMark.getMark3());
		Subject subject3 = new Subject();
		subject3.setCode("PHY13");
		sm3.setSubject(subject3);

		StudentMark sm4 = new StudentMark();

		sm4.setMark(updateMark.getMark4());
		Subject subject4 = new Subject();
		subject4.setCode("CHE14");
		sm4.setSubject(subject4);

		StudentMark sm5 = new StudentMark();

		sm5.setMark(updateMark.getMark5());
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
			studentValidator.isRegnoUpdated(updateMark.getRegno());

			userService.updateMarksAndGradeService(updateMark.getRegno(), list);

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
