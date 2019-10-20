package com.mani.gradingappapi.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mani.gradingappapi.model.Subject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectRepositoryTest {

	@Autowired
	private SubjectRepository subjectRepository;
	
	/** 
	 * findBySubjectCode in SubjectRepository
	 * @Param SubjectCode
	 * return Subject Object
	 */
	@Test
	public void findBySubjectCodeTest() {
		
		String subCode = "MAT12";
		
		Subject studentMarkList = subjectRepository.findBySubCode(subCode);
		
		assertNotNull(studentMarkList);
	      
	}

}
