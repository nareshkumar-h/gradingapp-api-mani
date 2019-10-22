package com.revature.gradingapp.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.gradingapp.model.Fsubject;
import com.revature.gradingapp.repository.FSubjectRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FsubjectRepositoryTest {

	@Autowired
	private FSubjectRepository fSubjectRepository;
	
	/** 
	 * findByGrade in FSubjectRepository
	 * @Param userId and subjectId
	 * return FSubject Object
	 */
	@Test
	public void findByGradeTest() {
		
		int userId = 3;
		int subjectId = 1;
		
		Fsubject fSub = fSubjectRepository.findByUserIdAndSubId(userId, subjectId);
		
		assertNotNull(fSub);
	      
	}

}
