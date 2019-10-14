package com.mani.gradingappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.model.StudentMark;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.UserRepository;
import com.mani.gradingappapi.dto.StudentGradeDTO;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.util.MessageConstant;
import com.mani.gradingappapi.validator.UserValidator;
import com.mani.gradingappapi.dao.EmployeeDaoImpl;
import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.validator.EmployeeValidator;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidator uservalidator;
	
	public UserDetails userLogin(String name, String password) throws ServiceException {
		
		final String role = "T";
		
		UserDetails userdetail = null;
		
			try {
				uservalidator.userInput(name, password);
				userdetail = userRepository.login(name,password,role);
				
				if (userdetail == null) {
					throw new ServiceException(MessageConstant.INVALID_INPUT);
				}
			} catch (ValidatorException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
			
		
		return userdetail;

	}

	public void updateEmployeeService(UserDetails user) throws ServiceException{
		
		EmployeeValidator employeeValidator = new EmployeeValidator();
		EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
		try {
			
			employeeValidator.addedEmployeeValidation(user);
			employeeDao.updateEmployee(user);
			
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
		
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
