package com.mani.gradingappapi.dao;

import com.mani.gradingappapi.exception.DBException;

public interface ValidatorDao {

	int findRegNo(int regno) throws DBException;

	String isMarkUpdated(int regno) throws DBException;

}