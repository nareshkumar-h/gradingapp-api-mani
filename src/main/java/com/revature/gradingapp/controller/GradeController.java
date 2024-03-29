package com.revature.gradingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gradingapp.dto.StudentGradeDTO;
import com.revature.gradingapp.exception.ServiceException;
import com.revature.gradingapp.exception.ValidatorException;
import com.revature.gradingapp.service.UserService;
import com.revature.gradingapp.util.Message;
import com.revature.gradingapp.validator.GradeValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("grade")
public class GradeController {

	@Autowired
	private UserService userService;
	@Autowired
	private GradeValidator gradeValidator;
	
	@GetMapping("/gradeWiseList")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Grade API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> gradeWiseList() {
	
		List<StudentGradeDTO> list = null;
		String errorMessage = "";
		String status = "";
	
		try {
			list = userService.listOfStudentService();
			status = "success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}
	
		if (status.equals("success")) {
			return new ResponseEntity<>(list, HttpStatus.OK );
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST );
		}
	}
	
	@GetMapping("/SpecficGradeWiseList")
	//@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Grade API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> SpecficGradeWiseList(@RequestParam("grade") String grade) {
		
		List<StudentGradeDTO> list = null;
		String errorMessage = "";
		String status = "";

		try {
			// grade Validation
			gradeValidator.gradeCheck(grade.toUpperCase());

			// call Service class and get the result
			list = userService.findByGradeService(grade.toUpperCase());

			status = "success";

		}catch (ServiceException e) {
			errorMessage = e.getMessage();
		} catch (ValidatorException e) {
			errorMessage = e.getMessage();
		} 

		if (status.equals("success")) { 
			return new ResponseEntity<>(list, HttpStatus.OK );
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST );
		}
	}
}
