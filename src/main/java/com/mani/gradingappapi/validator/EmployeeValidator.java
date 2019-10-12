package com.mani.gradingappapi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mani.gradingappapi.dao.ValidatorDao;
import com.mani.gradingappapi.dao.ValidatorDaoImpl;
import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.exception.ValidatorException;
import com.mani.gradingappapi.model.UserDetails;


public class EmployeeValidator {

	private final ValidatorDao validatordao= new ValidatorDaoImpl();
	
	private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,20})";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; 
    
    public EmployeeValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }
	
    public boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    public static boolean isValid(String email) 
    { 
        Pattern pat = Pattern.compile(EMAIL_REGEX); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    
	public void addedEmployeeValidation( UserDetails user) throws ValidatorException, DBException {
			
		if (user.getName() == null || "".equals(user.getName().trim())) {
			throw new ValidatorException("Invalid Name");
		} 
			
		Boolean valid = isValid(user.getEmail());
		if ( valid == false ) 
			throw new ValidatorException("Invalid Mail Id");
			
		try {
			String email = validatordao.findByEmail(user.getEmail());
			if( email.equals("exist") )
				throw new ValidatorException("Mail Id already exist");
		}catch(DBException e) {
			throw new ValidatorException(e.getMessage());
		}
			
		try {
			String mobNo = validatordao.findByMobNo(user.getMobno());
			if( mobNo.equals("exist") )
				throw new ValidatorException("Mobile Number already exist");
		}catch(DBException e) {
			throw new ValidatorException(e.getMessage());
		}
			
		if (user.getPassword() == null || "".equals(user.getPassword().trim()))
			 throw new ValidatorException("Invalid Password"); 
		
		Boolean res = validate(user.getPassword());
		if ( !res )
			throw new ValidatorException("Password must contain at least one number, one uppercase, one lowercase and at least 8 or more characters"); 
		   
		if (user.getRole() == null || "".equals(user.getRole().trim()) || user.getRole().length() != 1) 
			throw new ValidatorException("Invalid Role");
			
		}
}
