package com.mani.gradingappapi.validator;

import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.util.MessageConstant;

public class UserValidator {

	public void userInput(String name, String password) throws ValidatorException {

		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException(MessageConstant.INVALID_NAME);
		} else if (password == null || "".equals(name.trim())) {
			throw new ValidatorException(MessageConstant.INVALID_PASSWORD);
		}
	}

}

