package com.revature.gradingapp.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.gradingapp.model.ScoreRange;
import com.revature.gradingapp.repository.ScoreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreRepositoryTest {

	@Autowired
	private ScoreRepository scoreRepository;
	
	/** 
	 * findByAverage in ScoreRepository
	 * @Param userId and subjectId
	 * return FSubject Object
	 */
	@Test
	public void findByAverageTest() {
		
		float avg = 90;
		
		String grade = scoreRepository.findByAverage(avg);
		
		assertNotNull(grade);
	      
	}
	
	/** 
	 * findByGrade in ScoreRepository
	 * @Param grade
	 * return FSubject Object
	 */
	@Test
	public void findByGradeTest() {
		
		String grade = "A";
		
		ScoreRange isGradeExist = scoreRepository.findByGrade(grade);
		
		assertNotNull(isGradeExist);
	      
	}
	
	/** 
	 * findByGrade in ScoreRepository
	 * @Param range
	 * return FSubject Object
	 */
	@Test
	public void findRangeTest() {
		
		int range = 99;
		
		ScoreRange scoreRange = scoreRepository.findRange(range);
		
		assertNotNull(scoreRange);
	      
	}
}
