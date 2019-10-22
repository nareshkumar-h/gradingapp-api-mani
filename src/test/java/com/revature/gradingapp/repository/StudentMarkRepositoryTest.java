package com.revature.gradingapp.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.gradingapp.model.StudentMark;
import com.revature.gradingapp.repository.StudentMarkRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMarkRepositoryTest {

		@Autowired
		private StudentMarkRepository studentMarkRepository;
		
		/** 
		 * findBySubjectCode in StudentMarkRepository
		 * @Param SubjectCode
		 * return List of StudentMark Object
		 */
		@Test
		public void findBySubjectCodeTest() {
			
			String subCode = "MAT12";
			
			List<StudentMark> studentMarkList = studentMarkRepository.findBySubjectCode(subCode);
			
			assertNotNull(studentMarkList);
		      
		}

		/** 
		 * findByRegNo in StudentMarkRepository
		 * @Param regNo
		 * return List of StudentMark Object
		 */
		@Test
		public void findByRegNoTest() {
			
			int regNo = 1002;
			
			List<StudentMark> studentMarkList = studentMarkRepository.findByRegNo(regNo);
			
			assertNotNull(studentMarkList);
		      
		}
}
