package com.mani.gradingappapi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.dao.ValidatorDao;
import com.mani.gradingappapi.dao.ValidatorDaoImpl;
import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.repository.ScoreRepository;

@Service
public class GradeValidator {
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	ValidatorDao validatordao= new ValidatorDaoImpl();

public void isGradeExist(String grade, int min, int max) throws ValidatorException, DBException{
		
	System.out.println(grade);
		if (grade == null || "".equals(grade.trim()) || grade.length() != 1) 
			throw new ValidatorException("Invalid grade, Please try again");
		
		ScoreRange isExist = scoreRepository.findByGrade(grade);
		if(isExist != null)
			throw new ValidatorException("This Grade already updated, Please try another.");
		
		if( min > 100 || min < 0 || max > 100 || max < 0)
			throw new ValidatorException("Out of boundaries, Please enter the valid Range.");
		else if(min > max)
			throw new ValidatorException("Minimum Range is Greater than Maximum Range, Please enter the valid Range.");
		
		ScoreRange scorerange1 = scoreRepository.findRange(min);
		if(scorerange1 != null)
			throw new ValidatorException("Minimum range already updated, Please try another.");
	
		ScoreRange scorerange2 = scoreRepository.findRange(max);
		if(scorerange2 != null)
			throw new ValidatorException("Maximum range already updated, Please try another.");
	}

	public void gradeCheck(String grade) throws ValidatorException {
		
		if (grade == null || "".equals(grade.trim()) || grade.length() != 1) 
			throw new ValidatorException("Invalid grade, Please try again");
	
	}
	
}
