package com.mani.gradingappapi.util;

import java.util.List;

import com.mani.gradingappapi.dto.StudentGradeDTO;
import com.mani.gradingappapi.model.StudentDetail;
import com.mani.gradingappapi.model.StudentMark;

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
