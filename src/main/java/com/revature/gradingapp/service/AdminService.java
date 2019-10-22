package com.revature.gradingapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gradingapp.exception.ServiceException;
import com.revature.gradingapp.exception.ValidatorException;
import com.revature.gradingapp.model.Fsubject;
import com.revature.gradingapp.model.ScoreRange;
import com.revature.gradingapp.model.Subject;
import com.revature.gradingapp.model.UserDetails;
import com.revature.gradingapp.repository.FSubjectRepository;
import com.revature.gradingapp.repository.ScoreRepository;
import com.revature.gradingapp.repository.UserRepository;
import com.revature.gradingapp.util.MessageConstant;
import com.revature.gradingapp.validator.EmployeeValidator;
import com.revature.gradingapp.validator.GradeValidator;
import com.revature.gradingapp.validator.UserValidator;

@Service
public class AdminService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private UserRepository userRepository;
	
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

	/** 
	 * Admin Login in AdminService
	 * @Param name, password
	 * If the credential is Invalid, return ServiceException
	 * If the credential is valid, return UserDetails object
	 */
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
	
	/** 
	 * Add employee in AdminService
	 * @Param UserDetails object
	 * 
	 * If the credential is Invalid, return ServiceException
	 * 
	 * If the credential is valid, insert UserDetails object
	 */
	public void addEmployeeService(UserDetails user) throws ServiceException {

		try {
			System.out.println(user);
			employeeValidator.addedEmployeeValidation(user);
			
			userRepository.save(user);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/** 
	 * Define score range in AdminService
	 * @Param grade, min and max 
	 * 
	 * If the credential is Invalid, return ServiceException
	 * 
	 * If the credential is valid, insert ScoreRange object
	 */
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
		
	/** 
	 * Delete score range in AdminService
	 * @Param No 
	 */
	public void deleteScoreRangeService() throws ServiceException{

		scoreRepository.deleteAll();
	}

	/** 
	 * View score range in AdminService
	 * @Param No 
	 * 
	 * return List<ScoreRange>
	 */
	public List<ScoreRange> viewScoreRangeService() throws ServiceException{

		List<ScoreRange> list = null;
		
			list = scoreRepository.findAll();
			
			if( list.size() == 0 )
				throw new ServiceException(MessageConstant.SCORE_RANGE_EMPTY);
		
		return list;
	}

	/** 
	 * Update Employee Subject Service in AdminService
	 * @Param userId and subjectId
	 * 
	 * If the credential already updated, return ServiceException
	 * 
	 * If the credential is valid, insert Fsubject object
	 */
	public void updateEmployeeSubjectService(int userId, int subjectId) throws ServiceException {
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
