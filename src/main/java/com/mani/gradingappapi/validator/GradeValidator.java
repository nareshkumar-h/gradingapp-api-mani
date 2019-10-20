package com.mani.gradingappapi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.repository.ScoreRepository;
import com.mani.gradingappapi.util.MessageConstant;

@Service
public class GradeValidator {
	
	@Autowired
	private ScoreRepository scoreRepository;
	
public void isGradeExist(String grade, int min, int max) throws ValidatorException{
		
	System.out.println(grade);
		if (grade == null || "".equals(grade.trim()) || grade.length() != 1) 
			throw new ValidatorException("Invalid grade, Please try again");
		
		ScoreRange isExist = scoreRepository.findByGrade(grade);
		if(isExist != null)
			throw new ValidatorException(MessageConstant.GRADE_ALREADY_UPDATED);
		
		if( min > 100 || min < 0 || max > 100 || max < 0)
			throw new ValidatorException("Out of boundaries, Please enter the valid Range.");
		else if(min > max)
			throw new ValidatorException(MessageConstant.MINIMUM_RANGE_GREATER);
		
		ScoreRange scorerange1 = scoreRepository.findRange(min);
		if(scorerange1 != null)
			throw new ValidatorException(MessageConstant.MIN_ALREADY_UPDATED);
	
		ScoreRange scorerange2 = scoreRepository.findRange(max);
		if(scorerange2 != null)
			throw new ValidatorException(MessageConstant.MAX_ALREADY_UPDATED);
	}

	public void gradeCheck(String grade) throws ValidatorException {
		
		if (grade == null || "".equals(grade.trim()) || grade.length() != 1) 
			throw new ValidatorException(MessageConstant.INVALID_GRADE);
	}
	
}
