package com.mani.gradingappapi.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.Subject;
import com.mani.gradingappapi.repository.SubjectRepository;

public class SubjectValidator {
	
	@Autowired
	private SubjectRepository subjectRepository;

	public void subjectWiseRankHolder(String subCode) throws ValidatorException, DBException {

		if (subCode == null || "".equals(subCode.trim()) || subCode.length() != 5)
			throw new ValidatorException("Invalid Subject Code");
		
		List<Subject> subjectsList = null;
		
		subjectsList = subjectRepository.findAll();
		
		for (Subject subject : subjectsList) {
			
			if (!subject.getCode().equalsIgnoreCase(subCode)) 
				throw new ValidatorException("This Subject code is not exist");
		}	
	}

}
