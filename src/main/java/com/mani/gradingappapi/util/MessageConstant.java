package com.mani.gradingappapi.util;

public interface MessageConstant {
	
	//ConnectionUtil Messages
	public static final String DRIVER_CLASS = "Unable to load the driver class";
	
	public static final String SQL_CONNECTION = "Unable to get Connection";

	public static final String CLOSE_CONNECTION = "Unable to close the Connection";
	
	//Service Messages
	public static final String INVALID_INPUT = "Invalid Username and password, Please enter the valid one";
	
	//DAO Messages
	public static final String UNABLE_TO_LOGIN = "Unable to Login";
	
	public static final String UNABLE_TO_GET_RANGE = "Unable get the Score-Range";
	
	public static final String UNABLE_TO_CHECK = "Unable to Check";
	
	public static final String UNABLE_TO_GET_RECORDS = "Unable to get the records";
	
	//StudentGradeDao
	public static final String UNABLE_TO_INSERT_GRADE = "Unable to update the grade";
	
	//StudentMarkDao
	public static final String UNABLE_TO_INSERT_MARK ="Unable to update the marks";
	
	//SubjectDao 
	public static final String UNABLE_TO_GET_SUBJECTS = "Unable to get the Subject";
	
	//AdminDao
	public static final String UNABLE_TO_UPDATE_SCORE = "Unable to update Score-Range";
	
	public static final String UNABLE_TO_DELETE_SCORE = "Unable to delete the Score-Range";
	
	//Employee Dao
	public static final String UNABLE_TO_ADD = "Unable to add employee detail";
	
	public static final String UNABLE_TO_UPDATE = "Unable to update employee detail";
}
