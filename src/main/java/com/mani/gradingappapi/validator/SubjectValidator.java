package com.mani.gradingappapi.validator;

import java.util.List;

import com.mani.gradingappapi.dao.SubjectDaoImpl;
import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.Subject;

public class SubjectValidator {

	public void subjectWiseRankHolder(String subCode) throws ValidatorException, DBException {

		if (subCode == null || "".equals(subCode.trim()) || subCode.length() != 5)
			throw new ValidatorException("Invalid Subject Code");
		
		List<Subject> subjectsList = null;
		try {
			subjectsList = new SubjectDaoImpl().findAll();
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		}
		for (Subject subject : subjectsList) {
			
			if (!subject.getCode().equalsIgnoreCase(subCode)) 
				throw new ValidatorException("This Subject code is not exist");
		}	
	}

}
