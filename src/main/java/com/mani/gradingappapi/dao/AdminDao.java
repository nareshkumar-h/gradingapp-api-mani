package com.mani.gradingappapi.dao;


import java.util.List;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.model.UserDetails;

public interface AdminDao {

	UserDetails findAdminByNamePassword(String name, String pwd) throws DBException;

	int updateScoreRange(String grade, int min, int max) throws DBException;

	int deleteScoreRange() throws DBException;

	List<ScoreRange> viewScoreRange() throws DBException;

}
