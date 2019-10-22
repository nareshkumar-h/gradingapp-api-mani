package com.revature.gradingapp.util;

import java.util.List;

import com.revature.gradingapp.dto.StudentGradeDTO;
import com.revature.gradingapp.model.StudentDetail;
import com.revature.gradingapp.model.StudentMark;

import lombok.Data;

@Data
public class ResultResponseDto {

	private List<StudentMark> marks;
	
	private StudentGradeDTO studentGrade;

	public ResultResponseDto(List<StudentMark> marks, StudentGradeDTO studentGradeDTO) {
		super();
		this.marks = marks;
		this.studentGrade = studentGradeDTO;
	}
	

}
