package com.revature.gradingapp.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.gradingapp.exception.ValidatorException;
import com.revature.gradingapp.model.StudentMark;
import com.revature.gradingapp.repository.StudentMarkRepository;
import com.revature.gradingapp.util.MessageConstant;

public class StudentValidator {

	@Autowired
	private StudentMarkRepository studentMarkRepository;
	
	public void isRegnoUpdated(int regno) throws ValidatorException{

		List<StudentMark> list = null;
		list = studentMarkRepository.findByRegNo(regno);	
		
		if( list.size() != 0 )
			throw new ValidatorException(regno+ MessageConstant.ALREADY_UPDATED);
	}
	public void isRegnoExist(int regno) throws ValidatorException{
		
		List<StudentMark> list = null;
		list = studentMarkRepository.findByRegNo(regno);	
		
		System.out.println(list);
		if( list.size() == 0 )
			throw new ValidatorException(MessageConstant.MARK_DOESNOT_UPDATED);
		
	}
		

}