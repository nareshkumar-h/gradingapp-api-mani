package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;

public class StudentGradeDaoImpl {
	final String AVERAGE = "average";

	public String isGradeExist(String grd) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String grade = "";

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade from score_range where grade = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, grd);

			rs = pst.executeQuery();
			if (rs.next()) {
				grade = rs.getString("grade");
			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_RECORDS, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return grade;
	}
}
