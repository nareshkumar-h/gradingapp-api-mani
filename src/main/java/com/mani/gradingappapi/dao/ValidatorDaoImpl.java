package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;

public class ValidatorDaoImpl implements ValidatorDao {

	public int findRegNo(int regno) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		int reg_no = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select reg_no from student_details where reg_no = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);
			rs = pst.executeQuery();

			if (rs.next()) {
				reg_no = rs.getInt("reg_no");
			}

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_CHECK, e);
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return reg_no;
	}

	public String isMarkUpdated(int regno) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String status = "notexist";
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select marks from student_marks where reg_no = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);
			rs = pst.executeQuery();

			if (rs.next()) {
				status = "exist";
			}

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_CHECK, e);
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return status;
	}

}
