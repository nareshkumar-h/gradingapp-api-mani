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
import com.mani.gradingappapi.model.Fsubject;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.model.Subject;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.AdminRepository;
import com.mani.gradingappapi.repository.FSubjectRepository;
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
	
	@Autowired
	private FSubjectRepository fSubjectRepository;

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
		} catch (DBException e) {
			e.printStackTrace();
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

	public void updateEmpolyeeSubjectService(int userId, int subjectId) throws ServiceException {
		System.out.println("UserId="+ userId + ",subjectId=" + subjectId);
		Fsubject fSub = new Fsubject();
		fSub = fSubjectRepository.findByUserIdAndSubId(userId, subjectId);
		System.out.println(fSub);
		if( fSub != null )
			throw new ServiceException("This subject already allocated for this faculty");
			
		UserDetails user = new UserDetails();
		Subject sub = new Subject();
		Fsubject fSubject = new Fsubject();
		
		user.setId(userId);
		sub.setId(subjectId);
		
		fSubject.setUserDetails(user);
		fSubject.setSubject(sub);
		
		fSubjectRepository.save(fSubject);
		
	}
}
