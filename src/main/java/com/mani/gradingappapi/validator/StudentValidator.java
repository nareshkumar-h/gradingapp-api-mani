package com.mani.gradingappapi.validator;


import com.mani.gradingappapi.dao.ValidatorDao;
import com.mani.gradingappapi.dao.ValidatorDaoImpl;
import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ValidatorException;

public class StudentValidator {

	public void isRegnoUpdated(int regno) throws ValidatorException{

		int regNo = 0;
		String status = "";
		ValidatorDao validator = new ValidatorDaoImpl();
		try {
			regNo = validator.findRegNo(regno);
			status = validator.isMarkUpdated(regno);
		} catch (DBException e) {
			throw new ValidatorException(e.getMessage());
		}	
		
		if(regno != regNo)
			throw new ValidatorException("Register Number doesn't Exist");
		if(status.equals("exist") )
			throw new ValidatorException(regno+" already Updated..");
	}
	public void isRegnoExistService(int regno) throws ValidatorException{

		int regNo = 0;
		ValidatorDao validator = new ValidatorDaoImpl();
		try {
			regNo = validator.findRegNo(regno);
		} catch (DBException e) {
			throw new ValidatorException(e.getMessage());
		}	
		
		if(regno != regNo)
			throw new ValidatorException("Register Number doesn't Exist");
		
	}
		

	public int ischangeInteger(String reg) throws ValidatorException {

		int regno = 0;
		
		try {
			regno = Integer.parseInt(reg);
		} catch (Exception e) {
			throw new ValidatorException("Invalid Reg-No, Please try again.");
		}
		return regno;
	}
}
