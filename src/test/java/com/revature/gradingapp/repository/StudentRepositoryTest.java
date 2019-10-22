package com.revature.gradingapp.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.gradingapp.model.StudentDetail;
import com.revature.gradingapp.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	/** 
	 * findByRegNo in StudentRepository
	 * @Param regNo
	 * return StudentDetails Object
	 */
	@Test
	public void findByRegNoTest() {
		
		int regNo = 1002;
		
		StudentDetail studentList = studentRepository.findByRegNo(regNo);
		
		assertNotNull(studentList);
	      
	}

}
