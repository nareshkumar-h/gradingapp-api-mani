package com.revature.gradingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gradingapp.dto.StudentGradeDTO;
import com.revature.gradingapp.exception.ValidatorException;
import com.revature.gradingapp.model.StudentMark;
import com.revature.gradingapp.service.UserService;
import com.revature.gradingapp.util.Message;
import com.revature.gradingapp.util.ResultResponseDto;
import com.revature.gradingapp.validator.StudentValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ResultController {
	@Autowired
	private UserService userService;

	@Autowired
	private StudentValidator studentValidator;
	
	@GetMapping("studentResult")
	//@ResponseStatus ( code = HttpStatus.OK)
	@ApiOperation(value = "Result API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated mark", response = ResultResponseDto.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> studentResult(@RequestParam("regno")int regno){
		List<StudentMark> markList = null;
		StudentGradeDTO studentResult = null;
		try {
			studentValidator.isRegnoExist(regno);
			
			studentResult = userService.getStudentResult(regno);
			markList = userService.getStudentMarks(regno);
			
			ResultResponseDto result = new ResultResponseDto(markList, studentResult);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (ValidatorException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
