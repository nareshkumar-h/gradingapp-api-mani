package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;
import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.model.UserDetails;

public class AdminDaoImpl implements AdminDao {

	public UserDetails findAdminByNamePassword(String name, String pwd) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		UserDetails userdetail = null;
		ResultSet rs = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select name, email, mob_no, role, subject from app_users where name = ? and password = ? and role = 'A'";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pwd);
			rs = pst.executeQuery();

			if (rs.next()) {
				userdetail = new UserDetails();

				userdetail.setName(rs.getString("name"));
				userdetail.setEmail(rs.getString("email"));
				userdetail.setMobno(rs.getLong("mob_no"));
				userdetail.setRole(rs.getString("role"));
				userdetail.setSubject(rs.getString("subject"));
			}

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_LOGIN, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return userdetail;
	}

	public int updateScoreRange(String grade, int min, int max) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		int rows = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into score_range (grade, min, max) values (?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, grade);
			pst.setInt(2, min);
			pst.setInt(3, max);
			
			rows = pst.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_UPDATE_SCORE, e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return rows;
	}

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

	public List<ScoreRange> viewScoreRange() throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ScoreRange scorerange = null;
		ResultSet rs = null;
		List<ScoreRange> list= null;
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade, min, max from score_range";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			list = new ArrayList<ScoreRange>();
			
			while (rs.next()) {
				scorerange = new ScoreRange();
				
				scorerange.setGrade(rs.getString("grade"));
				scorerange.setMin(rs.getInt("min"));
				scorerange.setMax(rs.getInt("max"));
				
				list.add(scorerange);
			}

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_RANGE, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return list;
	}

}
