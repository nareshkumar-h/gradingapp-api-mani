package com.mani.gradingappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.dao.EmployeeDaoImpl;
import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.UserRepository;
import com.mani.gradingappapi.util.MessageConstant;
import com.mani.gradingappapi.validator.EmployeeValidator;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;

	public UserDetails adminLogin(String name, String pwd) throws ServiceException {
		
		final String role = "A";
		UserDetails userdetail = null;
		
		userdetail = userRepository.login(name,pwd,role);
			
			if (userdetail == null) {
				throw new ServiceException(MessageConstant.INVALID_INPUT);
			}
		return userdetail;
		
	}
	
	public void addEmployeeService(UserDetails user) throws ServiceException {

		EmployeeValidator employeeValidator = new EmployeeValidator();
		try {
			employeeValidator.addedEmployeeValidation(user);
			final EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
			employeeDao.addEmployee(user);
			
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateScoreRangeService(String upperCase, int min, int max) throws ServiceException{
		// TODO Auto-generated method stub
		
	}

	public void deleteScoreRangeService() throws DBException{
		// TODO Auto-generated method stub
		
	}

	public List<ScoreRange> viewScoreRangeService() throws ServiceException{
		// TODO Auto-generated method stub
		return null;
	}
}
