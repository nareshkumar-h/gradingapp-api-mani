package com.mani.gradingappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.AdminRepository;
import com.mani.gradingappapi.repository.UserRepository;
import com.mani.gradingappapi.util.MessageConstant;
import com.mani.gradingappapi.validator.EmployeeValidator;
import com.mani.gradingappapi.validator.GradeValidator;
import com.mani.gradingappapi.validator.UserValidator;
import com.mani.gradingappapi.dao.AdminDao;
import com.mani.gradingappapi.dao.AdminDaoImpl;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private EmployeeValidator employeeValidator;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private GradeValidator gradeValidator;

	public UserDetails adminLogin(String name, String pwd) throws ServiceException {
		
		final String role = "A";
		UserDetails userdetail = null;
		
		try {
			userValidator.userInput(name, pwd);
			userdetail = userRepository.login(name,pwd,role);
			
			if (userdetail == null) {
				throw new ServiceException(MessageConstant.INVALID_INPUT);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return userdetail;
		
	}
	
	public void addEmployeeService(UserDetails user) throws ServiceException {

		try {
			employeeValidator.addedEmployeeValidation(user);
//			final EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
//			employeeDao.addEmployee(user);
			
			adminRepository.insertEmployee(user);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void defineScoreRangeService(String grade, int min, int max) throws ServiceException{
		
		try {
			
			gradeValidator.isGradeExist(grade, min, max);
			
			adminRepository.defineScore(grade, min, max);
			
			
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
	}
		

	public void deleteScoreRangeService() throws ServiceException{

		AdminDao admindao = new AdminDaoImpl();
		
		try {
			admindao.deleteScoreRange();
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	public List<ScoreRange> viewScoreRangeService() throws ServiceException{

		List<ScoreRange> list = null;
		
			list = adminRepository.viewScore();
			
			if( list == null )
				throw new ServiceException(MessageConstant.UNABLE_TO_DELETE_SCORE);
		
		return list;
	}
}
