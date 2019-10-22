package com.revature.gradingapp.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.gradingapp.dto.StudentGradeDTO;
import com.revature.gradingapp.exception.ServiceException;
import com.revature.gradingapp.model.StudentMark;
import com.revature.gradingapp.model.Subject;
import com.revature.gradingapp.model.UserDetails;
import com.revature.gradingapp.service.AdminService;
import com.revature.gradingapp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private UserService userService;
	
	/** 
	 * Invalid
	 * userLogin in UserService
	 * @Param name and password
	 * return UserDetails Object
	 */
	@Test
	public void userLoginInValidTest() {
		String name = "Admin";
		String pwd = "Admin123";
		
		UserDetails userDetails = null;
		try {
			userDetails = userService.userLogin(name, pwd);
		} catch (ServiceException e) {
			LOGGER.debug(e.getMessage());
		}
		assertNull(userDetails);
	}
	
	/** 
	 * Valid
	 * userLogin in UserService
	 * @Param name and password
	 * return UserDetails Object
	 */
	@Test
	public void userLoginValidTest() {
		String name = "Mano";
		String pwd = "Mano123";
		
		UserDetails userDetails = null;
		try {
			userDetails = userService.userLogin(name, pwd);
		} catch (ServiceException e) {
			LOGGER.debug(e.getMessage());
		}
		assertNotNull(userDetails);
	}
	
	/** 
	 * listOfStudentService in UserService
	 * @Param No
	 * return List<StudentGradeDTO>
	 */
	@Test
	public void listOfStudentServiceTest() {
		List<StudentGradeDTO> listOfStudent = null;
		try {
			listOfStudent = userService.listOfStudentService();
		} catch (ServiceException e) {
			LOGGER.debug(e.getMessage());
		}
		assertNotNull(listOfStudent);
	}
	
	/** 
	 * findBySubjectCodeService in UserService
	 * @Param subCode
	 * return List<StudentMark>
	 */
	@Test
	public void findBySubjectCodeServiceTest() {
		List<StudentMark> listOfStudent = null;
		String subCode = "MAT12";
		listOfStudent = userService.findBySubjectCodeService(subCode);
		assertNotNull(listOfStudent);
	}
	
	/** 
	 * getStudentResult in UserService
	 * @Param regno
	 * return StudentGradeDTO object
	 */
	@Test
	public void getStudentResultTest() {
		StudentGradeDTO dto = null;
		int regno = 1001;
		dto = userService.getStudentResult(regno);
		assertNotNull(dto);
	}
	
	/** 
	 * getStudentMarks in UserService
	 * @Param regno
	 * return List<StudentMark>
	 */
	@Test
	public void getStudentMarksTest() {
		List<StudentMark> studentMarkList = null;
		int regno = 1001;
		studentMarkList = userService.getStudentMarks(regno);
		assertNotNull(studentMarkList);
	}

	/** 
	 * findByGradeService in UserService
	 * @Param grade
	 * return List<StudentGradeDTO>
	 */
	@Test
	public void findByGradeServiceTest() {
		List<StudentGradeDTO> dto = null;
		String grade = "A";
		try {
			dto = userService.findByGradeService(grade);
		} catch (ServiceException e) {
			LOGGER.debug(e.getMessage());
		}
		assertNotNull(dto);
	}
	
	/** 
	 * listOfUser in UserService
	 * @Param No
	 * return List<UserDetails>
	 */
	@Test
	public void listOfUserTest() {
		List<UserDetails> userDetailsList = null;
		userDetailsList = userService.listOfUser();
		assertNotNull(userDetailsList);
	}
	
	/** 
	 * subjectListService in UserService
	 * @Param No
	 * return List<UserDetails>
	 */
	@Test
	public void subjectListServiceTest() {
		List<Subject> subjectList = null;
		subjectList = userService.subjectListService();
		assertNotNull(subjectList);
	}
}
