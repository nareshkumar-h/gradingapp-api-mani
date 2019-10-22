package com.revature.gradingapp.util;

public interface MessageConstant {
	
	/* Service Messages */
	public static final String INVALID_INPUT = "Invalid Username and password, Please enter the valid one";
	public static final String SCORE_RANGE_EMPTY = "No score range updated";
	
	/* UserService Messages */
	public static final String NO_RECORDS_AVAILABLE = "No record available.";
	
	/* GradeValidator */
	public static final String GRADE_ALREADY_UPDATED = "This Grade already updated, Please try another.";
	public static final String MINIMUM_RANGE_GREATER = "Minimum Range is Greater than Maximum Range, Please enter the valid Range.";
	public static final String MIN_ALREADY_UPDATED = "Minimum range already updated, Please try another.";
	public static final String MAX_ALREADY_UPDATED = "Minimum range already updated, Please try another.";
	public static final String INVALID_GRADE = "Invalid grade, Please try again";
	
	/* EmployeeValidator */
	public static final String MAIL_AREADY_EXIST = "Mail Id already exist";
	public static final String MOBILE_AREADY_EXIST = "Mobile Number already exist";
	public static final String INVALID_PASSWORD = "Invalid Password";
	public static final String PWD_MUST_BE_THE_FORMAT = "Password must contain at least one number, one uppercase, one lowercase and at least 8 or more characters";
	public static final String INVALID_ROLE = "Invalid Role";
	
	/* StudentValidator */
	public static final String ALREADY_UPDATED = " already Updated";
	public static final String MARK_DOESNOT_UPDATED = "This register number mark does not updated";
	
	/* SubjetcValidator */
	public static final String INVALID_SUB_CODE = "Invalid Subject Code";
	public static final String SUB_CODE_NOT_EXIST = "This Subject code is not exist";
	
	/* UserValidator */
	public static final String INVALID_NAME = "Invalid Name";
	
}
