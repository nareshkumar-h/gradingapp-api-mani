package com.mani.gradingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.dto.StudentGradeDTO;
import com.mani.gradingappapi.model.StudentDetail;
import com.mani.gradingappapi.model.StudentMark;
import com.mani.gradingappapi.service.UserService;
import com.mani.gradingappapi.util.Message;
import com.mani.gradingappapi.util.ResultResponseDto;
import com.mani.gradingappapi.validator.StudentValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ResultController {
	@Autowired
	private UserService userService;

	@GetMapping("studentResult")
	//@ResponseStatus ( code = HttpStatus.OK)
	@ApiOperation(value = "Result API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated mark", response = ResultResponseDto.class),
			@ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> studentResult(@RequestParam("regno")int regno){
		List<StudentMark> markList = null;
		StudentGradeDTO studentResult = null;
		StudentDetail studentDetail = null;
		try {
			
			// Reg-No validator
			StudentValidator studentValidate = new StudentValidator();
			studentValidate.isRegnoExistService(regno);
			
			//get the StudentName, Average, Grade
			studentResult = userService.getStudentResult(regno);
			
			//get the Marks and Sub-Code
			markList = userService.getStudentMarks(regno);
			
			for (StudentMark studentMark : markList) {
				studentDetail = studentMark.getStudentDetail();
			}
			
			ResultResponseDto result = new ResultResponseDto(markList, studentResult);
			//json = "{\"marks\"" +":"+ "" + json1+ ", \"SD\""+":" + "" + json2+ "}"; 
			//convert list to json
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
