package com.revature.gradingapp.validator;

import com.revature.gradingapp.exception.ValidatorException;
import com.revature.gradingapp.util.MessageConstant;

public class UserValidator {

	public void userInput(String name, String password) throws ValidatorException {

		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException(MessageConstant.INVALID_NAME);
		} else if (password == null || "".equals(name.trim())) {
			throw new ValidatorException(MessageConstant.INVALID_PASSWORD);
		}
	}

}

