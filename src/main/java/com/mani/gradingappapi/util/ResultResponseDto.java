package com.mani.gradingappapi.util;

import java.util.List;

import com.mani.gradingappapi.dto.StudentGradeDTO;
import com.mani.gradingappapi.model.StudentMark;

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
