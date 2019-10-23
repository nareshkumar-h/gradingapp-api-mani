package com.revature.gradingapp.dto;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentGradeDTOTest {

	//@Test
	public void testStudentGradeDTO() {

		StudentGradeDTO studentGrade = new StudentGradeDTO();
		studentGrade.setRegNo(1001);
		studentGrade.setStudentName("Bala");
		studentGrade.setAvg(90);
		studentGrade.setGrade("A");
		assertEquals(studentGrade.getRegNo(), Double.valueOf(1001));
		assertEquals(studentGrade.getStudentName(), "Bala");
		//assertEquals(Double.valueOf(90), studentGrade.getAvg());
		assertEquals(studentGrade.getGrade(), "A");
	
	}

}
