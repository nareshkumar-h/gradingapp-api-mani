package com.mani.gradingappapi.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.dto.StudentGradeDTO;
import com.mani.gradingappapi.exception.ServiceException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.Grade;
import com.mani.gradingappapi.model.StudentDetail;
import com.mani.gradingappapi.model.StudentMark;
import com.mani.gradingappapi.model.Subject;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.GradeRepository;
import com.mani.gradingappapi.repository.ScoreRepository;
import com.mani.gradingappapi.repository.StudentMarkRepository;
import com.mani.gradingappapi.repository.StudentRepository;
import com.mani.gradingappapi.repository.SubjectRepository;
import com.mani.gradingappapi.repository.UserRepository;
import com.mani.gradingappapi.util.MessageConstant;
import com.mani.gradingappapi.validator.UserValidator;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidator uservalidator;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private StudentMarkRepository studentMarkRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	final String role = "T";
	
	/** 
	 * User Login in UserService
	 * @Param name, password
	 * If the credential is Invalid, return ServiceException
	 * If the credential is valid, return UserDetails object
	 */
	public UserDetails userLogin(String name, String password) throws ServiceException {
		
		UserDetails userdetail = null;
		
			try {
				uservalidator.userInput(name, password);
				userdetail = userRepository.login(name,password,role);
				
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
	 * list of Student based on grade Service in UserService
	 * @Param No
	 * 
	 * return List of StudentGradeDTO
	 */
	public List<StudentGradeDTO> listOfStudentService() throws ServiceException {
		
		List<StudentGradeDTO> listOfStudent = new ArrayList<StudentGradeDTO>();
		
		try {
			List<Grade> findAll = gradeRepository.findTopToBottomGrade();
			
			for (Grade studentGrade : findAll) {
				
				StudentGradeDTO dto = toStudentGradeDTO(studentGrade);
				
				listOfStudent.add(dto);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		return listOfStudent;
	}

	/** 
	 * @Param Grade object
	 * 
	 * return StudentGradeDTO (dto object)
	 */
	public StudentGradeDTO toStudentGradeDTO(Grade studentGrade) {
		StudentGradeDTO dto = new StudentGradeDTO();
		final StudentDetail studentDetail = studentGrade.getStudentDetail();
		dto.setRegNo(studentDetail.getRegNo());
		dto.setStudentName(studentDetail.getStudentName());
		dto.setAvg(studentGrade.getAverage());
		dto.setGrade(studentGrade.getGrade());
		return dto;
	}

	/** 
	 * find By SubjectCode Service in UserService
	 * @Param subCode
	 * 
	 * return List<StudentMark>
	 */
	public List<StudentMark> findBySubjectCodeService(String subCode) {
		return studentMarkRepository.findBySubjectCode(subCode);
	}

	/** 
	 * getStudentResult Service in UserService
	 * @Param regNo
	 * 
	 * return StudentGradeDTO object
	 */
	public StudentGradeDTO getStudentResult(int regno) {
		Grade grade = gradeRepository.findByRegNo(regno);
		StudentGradeDTO dto = toStudentGradeDTO(grade);
		return dto;
	}

	/** 
	 * getStudentMarks Service in UserService
	 * @Param regNo
	 * 
	 * return List<StudentMark>
	 */
	public List<StudentMark> getStudentMarks(int regno) {
		return studentMarkRepository.findByRegNo(regno);
	}

	/** 
	 * update Marks And Grade Service in UserService
	 * @Param regNo and List<StudentMark> marks
	 * 
	 * insert marks 
	 * calculate total, average and grade
	 * insert total, average and grade.
	 */
	public void updateMarksAndGradeService(int regno, List<StudentMark> marks) throws ServiceException {
		
		StudentDetail findByRegNo = studentRepository.findByRegNo(regno);
		
		for (StudentMark studentMark : marks) {
			Subject subjectCode = subjectRepository.findBySubCode(studentMark.getSubject().getCode()); 
			studentMark.setStudentDetail(findByRegNo);
			studentMark.setSubject(subjectCode);
			studentMarkRepository.save(studentMark);
		}
		
		int total = 0;
		for (StudentMark studentMark : marks) {
			total = total + studentMark.getMark();
		}

		float avg = (float)( total / marks.size());
		String gradeRange = scoreRepository.findByAverage(avg);
		
		Grade grade = new Grade();
		
		grade.setAverage(avg);
		grade.setGrade(gradeRange);
		grade.setStudentDetail(findByRegNo);
		
		gradeRepository.save(grade);
	}

	/** 
	 * findByGrade Service in UserService
	 * @Param grade
	 * 
	 * If the credential is Invalid, return ServiceException
	 * 
	 * If the credential is valid, return List<StudentGradeDTO> 
	 */
	public List<StudentGradeDTO> findByGradeService(String grade)  throws ServiceException{
		List<StudentGradeDTO> dtoList = new ArrayList<StudentGradeDTO>();
		List<Grade> specificGradeList = null;
		
			specificGradeList = gradeRepository.findByGrade(grade);

			System.out.println(specificGradeList);
			
			
				for (Grade studentGrade : specificGradeList) {
					StudentGradeDTO dto = toStudentGradeDTO(studentGrade);
					
					dtoList.add(dto);
				}
				
			if ( dtoList.size() == 0 )
				throw new ServiceException(MessageConstant.NO_RECORDS_AVAILABLE);

			return dtoList;
	}

	/** 
	 * listOfUser Service in UserService
	 * @Param No
	 * 
	 * return List<UserDetails>
	 */
	public List<UserDetails> listOfUser() {

		List<UserDetails> list = new ArrayList<UserDetails>();
		list = userRepository.findByRole(role);
		
		return list;
		
	}

	/** 
	 * subjectList Service in UserService
	 * @Param No
	 * 
	 * return List<Subject>
	 */
	public List<Subject> subjectListService() {

		List<Subject> list = new ArrayList<Subject>();
		
		list = subjectRepository.findAll();
		
		return list;
	}
}
