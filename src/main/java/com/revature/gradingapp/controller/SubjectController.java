package com.revature.gradingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gradingapp.model.StudentMark;
import com.revature.gradingapp.model.Subject;
import com.revature.gradingapp.service.UserService;
import com.revature.gradingapp.util.Message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SubjectController {
	@Autowired
	private UserService userFeature;
	
	@GetMapping("subjectWise")
	//@ResponseStatus ( code = HttpStatus.OK )
	@ApiOperation(value = "SubjectWiseList API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 201, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> subjectWiseRankHolder(@RequestParam("subjectCode")String subCode){
		
		List<StudentMark> list = null;
		String errorMessage = "";
		String status = "";
		list = userFeature.findBySubjectCodeService(subCode);
		status = "success";

		if (status.equals("success")) {
			return new ResponseEntity<>(list, HttpStatus.OK );
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST );
		}	
	}
	
	@GetMapping("subjectList")
	//@ResponseStatus ( code = HttpStatus.OK )
	@ApiOperation(value = "Subject API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = List.class) })
	public ResponseEntity<?> subjectList(){
		
		List<Subject> list = null;
		
			list = userFeature.subjectListService();

		return new ResponseEntity<>(list, HttpStatus.OK );
	}
}
