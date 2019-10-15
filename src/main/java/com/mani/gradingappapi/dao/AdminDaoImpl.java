package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;

public class AdminDaoImpl implements AdminDao {

	public int deleteScoreRange() throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		int rows = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "truncate table score_range";
			pst = con.prepareStatement(sql);
			
			rows = pst.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_DELETE_SCORE, e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return rows;
	}

}
