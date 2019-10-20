package com.mani.gradingappapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mani.gradingappapi.model.Grade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeRepositoryTest {

	@Autowired
	private GradeRepository gradeRepository;
	
	/** 
	 * findByRegNo in GradeRepository
	 * @Param regNo
	 * return Grade Object
	 */
	@Test
	public void findByRegNoTest() {
		
		int regNo = 1001;
		
		Grade grade = gradeRepository.findByRegNo(regNo);
		
		assertThat(grade.getStudentDetail().getRegNo()).isEqualTo(regNo);
	      
	}

	/** 
	 * findByGrade in GradeRepository
	 * @Param grade
	 * return List of Grade Object
	 */
	@Test
	public void findByGradeTest() {
		
		String grade = "A";
		
		List<Grade> gradeList = gradeRepository.findByGrade(grade);
		
		assertNotNull(gradeList);
	      
	}
	
	/** 
	 * findTopToBottomGrade in GradeRepository
	 * 
	 * return List of Grade Object
	 */
	@Test
	public void findTopToBottomGradeTest() {
		
		List<Grade> gradeList = gradeRepository.findTopToBottomGrade();
		
		assertNotNull(gradeList);
	      
	}
}
