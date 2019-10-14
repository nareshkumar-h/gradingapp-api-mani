package com.mani.gradingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.service.AdminService;
import com.mani.gradingappapi.util.Message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("score")
public class ScoreController {
	
	@Autowired
	private AdminService adminservice;
	
	@GetMapping("/defineScore")
	//@ResponseStatus ( code = HttpStatus.OK )
	@ApiOperation(value = "Score API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated score range", response = Message.class),
			@ApiResponse(code = 201, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> defineScore(@RequestParam("grade") String grade, @RequestParam("min") int min, @RequestParam("max") int max)
	{
		String errorMessage = null;
		String status = "";
		try {

			adminservice.defineScoreRangeService(grade.toUpperCase(), min, max);

			status = "Success";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}

		if (status.equals("Success")) {
			Message message = new Message();
			message.setInfoMessage(status);
			return new ResponseEntity<>(message, HttpStatus.OK);
			
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
		}
	}
	
	//@DeleteMapping("/deleteScore")
	@PostMapping("/deleteScore")
	//@ResponseStatus ( code = HttpStatus.NO_CONTENT )
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted", response = Message.class),
			@ApiResponse(code = 204, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?> deleteScore(){
		
		String status = "";
		String errorMessage = "";
		try {
			adminservice.deleteScoreRangeService();
			status = "Score Range Deleted..";
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
		}
	
		if (status.equals("Success")) {
			Message message = new Message(status);
			return new ResponseEntity<>(message, HttpStatus.OK);
			
		} else {
			Message message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/viewScore")
	@ResponseStatus ( code = HttpStatus.OK )
	@ApiOperation(value = "Score API")
	public ResponseEntity<?> viewScore()
	{
		List<ScoreRange> list = null;
		String errorMessage = null;
		String status = "";
		try {
			list = adminservice.viewScoreRangeService();
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
}
