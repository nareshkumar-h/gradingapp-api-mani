package com.mani.gradingappapi.dao;


import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.model.ScoreRange;

public interface ValidatorDao {

	ScoreRange findGrade(String grade) throws DBException;

	ScoreRange findRange(int min) throws DBException;

	int findRegNo(int regno) throws DBException;

	String isMarkUpdated(int regno) throws DBException;

	String findByEmail(String email) throws DBException;

	String findByMobNo(long mobno) throws DBException;

}