package com.mani.gradingappapi.dao;

import com.mani.gradingappapi.exception.DBException;

public interface AdminDao {

	int deleteScoreRange() throws DBException;

}
