package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.model.UserDetails;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;

public class EmployeeDaoImpl {

	public int addEmployee( UserDetails user ) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		int rows = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into app_users ( name, email, mob_no, password, role ) values (?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setLong(3, user.getMobno());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getRole());
			
			rows = pst.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_ADD, e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return rows;
	}

	public int updateEmployee(UserDetails user) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		int rows = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "update app_users set name = ?, mob_no = ?, password = ?, role= ? where email = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setLong(2, user.getMobno());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getRole());
			pst.setString(6, user.getEmail());
			
			rows = pst.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_UPDATE, e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return rows;
	}
}
