package com.mani.gradingappapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.AdminRepository;
import com.mani.gradingappapi.repository.ScoreRepository;
import com.mani.gradingappapi.repository.UserRepository;
import com.mani.gradingappapi.util.MessageConstant;
import com.mani.gradingappapi.validator.EmployeeValidator;
import com.mani.gradingappapi.validator.GradeValidator;
import com.mani.gradingappapi.validator.UserValidator;
import com.mani.gradingappapi.dao.AdminDao;
import com.mani.gradingappapi.dao.AdminDaoImpl;

@Service
public class AdminService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
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
			LOGGER.error("Exception:", e);
			throw new ServiceException(e.getMessage());
		}
		
		return userdetail;
		
	}
	
	public void addEmployeeService(UserDetails user) throws ServiceException {

		try {
			System.out.println(user);
			employeeValidator.addedEmployeeValidation(user);
			
			adminRepository.save(user);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void defineScoreRangeService(String grade, int min, int max) throws ServiceException{
		
		try {
			
			gradeValidator.isGradeExist(grade, min, max);
		
			ScoreRange scoreRange = new ScoreRange();
			scoreRange.setGrade(grade);
			scoreRange.setMax(max);
			scoreRange.setMin(min);
			
			System.out.println(scoreRange);
			
			scoreRepository.save(scoreRange);
			
			
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
		
			list = scoreRepository.findAll();
			
			if( list == null )
				throw new ServiceException(MessageConstant.UNABLE_TO_DELETE_SCORE);
		
		return list;
	}
}
