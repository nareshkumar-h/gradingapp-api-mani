package com.mani.gradingappapi.validator;

import com.mani.gradingappapi.exception.ValidatorException;

public class UserValidator {

	public void userInput(String name, String password) throws ValidatorException {

		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		} else if (password == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Password");
		}
	}

}

