package com.mani.gradingappapi.dto;

import lombok.Data;

@Data
public class StudentGradeDTO {

	private Integer regNo;
	
	private String studentName;
	
	private float avg;
	
	private String grade;

}
