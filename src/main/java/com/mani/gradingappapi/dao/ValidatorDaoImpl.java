package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;

public class ValidatorDaoImpl implements ValidatorDao {

	public ScoreRange findGrade(String grade) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ScoreRange scorerange = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade, min, max from score_range where grade = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, grade);
			rs = pst.executeQuery();

			if (rs.next()) {
				scorerange = new ScoreRange();
				scorerange.setGrade("grade");
			}

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_CHECK, e);
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return scorerange;
	}

	public ScoreRange findRange(int mark) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ScoreRange scorerange1 = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade, min, max from score_range where ? between min and max";
			pst = con.prepareStatement(sql);
			pst.setInt(1, mark);
			rs = pst.executeQuery();

			if (rs.next()) {
				scorerange1 = new ScoreRange();
				scorerange1.setGrade("grade");
			}

		} catch (SQLException e) {
			throw new DBException("Unable to find", e);
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return scorerange1;
	}

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

	public String findByEmail(String email) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String status = "notexist";
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select email from app_users where email = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
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

	public String findByMobNo(long mobno) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String status = "notexist";
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select mob_no from app_users where mob_no = ?";
			pst = con.prepareStatement(sql);
			pst.setLong(1, mobno);
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
