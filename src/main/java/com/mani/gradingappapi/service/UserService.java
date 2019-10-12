package com.mani.gradingappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.model.StudentMark;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.UserRepository;
import com.mani.gradingappapi.dto.StudentGradeDTO;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.util.MessageConstant;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDetails userLogin(String name, String pass) throws ServiceException {
		
		final String role = "T";
		
		UserDetails userdetail = null;
		
			userdetail = userRepository.login(name,pass,role);
		
		if (userdetail == null) {
			throw new ServiceException(MessageConstant.INVALID_INPUT);
		}
		
		return userdetail;

	}

	public void updateEmployeeService(UserDetails userDetails) throws ServiceException{
		// TODO Auto-generated method stub
		
	}

	public List<StudentGradeDTO> listOfStudentService() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StudentMark> findBySubjectCodeService(String subCode) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public StudentGradeDTO getStudentResult(int regno) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StudentMark> getStudentMarks(int regno) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateMarksAndGradeService(int regno, List<StudentMark> list) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public List<StudentGradeDTO> findByGradeService(String upperCase)  throws ServiceException{
		// TODO Auto-generated method stub
		return null;
	}
}
