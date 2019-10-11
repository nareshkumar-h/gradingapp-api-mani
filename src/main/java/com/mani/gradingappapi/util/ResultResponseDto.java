package com.mani.gradingappapi.util;

import java.util.List;

import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.model.StudentMark;

public class ResultResponseDto {

	public ResultResponseDto(List<StudentMark> marks, StudentGradeDTO sD) {
		super();
		this.marks = marks;
		SD = sD;
	}

	private List<StudentMark> marks;
	
	private StudentGradeDTO SD;

	public List<StudentMark> getMarks() {
		return marks;
	}

	public void setMarks(List<StudentMark> marks) {
		this.marks = marks;
	}

	public StudentGradeDTO getSD() {
		return SD;
	}

	public void setSD(StudentGradeDTO sD) {
		SD = sD;
	}
	
}
