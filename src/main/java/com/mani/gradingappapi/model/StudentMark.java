package com.mani.gradingappapi.model;

import com.mani.gradingappapi.dto.StudentGradeDTO;

public class StudentMark {

	private Integer id;
	
	private StudentDetail studentDetail;

	private Subject subject;
	
	private Integer mark;
	
	private StudentGradeDTO studentGradeDTO;
	
	public StudentGradeDTO getStudentGradeDTO() {
		return studentGradeDTO;
	}

	public void setStudentGradeDTO(StudentGradeDTO studentGradeDTO) {
		this.studentGradeDTO = studentGradeDTO;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

}
