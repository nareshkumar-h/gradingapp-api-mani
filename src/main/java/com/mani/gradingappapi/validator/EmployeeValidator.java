package com.mani.gradingappapi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.repository.UserRepository;
import com.mani.gradingappapi.util.MessageConstant;

@Service
public class EmployeeValidator {
	
	@Autowired
	private UserRepository userRepository;

    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,20})";
    
    public boolean validate(final String password) {
    	Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
	public void addedEmployeeValidation( UserDetails user) throws ValidatorException {
			
		if (user.getName() == null || "".equals(user.getName().trim())) {
			throw new ValidatorException("Invalid Name");
		} 
			
		UserDetails email = userRepository.findByEmail(user.getEmail());
		if( email != null )
			throw new ValidatorException(MessageConstant.MAIL_AREADY_EXIST);
			
		UserDetails mobileNo = userRepository.findByMobNo(user.getMobno());
		if( mobileNo != null )
			throw new ValidatorException(MessageConstant.MOBILE_AREADY_EXIST);
			
		if (user.getPassword() == null || "".equals(user.getPassword().trim()))
			 throw new ValidatorException(MessageConstant.INVALID_PASSWORD); 
		
		Boolean res = validate(user.getPassword());
		if ( !res )
			throw new ValidatorException(MessageConstant.PWD_MUST_BE_THE_FORMAT); 
		   
		if (user.getRole() == null || "".equals(user.getRole().trim())) 
			throw new ValidatorException(MessageConstant.INVALID_ROLE);
			
		}
}
