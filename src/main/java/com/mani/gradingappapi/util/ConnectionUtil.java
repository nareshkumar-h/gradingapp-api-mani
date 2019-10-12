package com.mani.gradingappapi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mani.gradingappapi.exception.DBException;

public class ConnectionUtil {
	

	private static final String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://trainingdb.ck1ayq0lncmp.ap-south-1.rds.amazonaws.com:3306/manikandan_db";
	private static final String USERNAME = "manikandan";
	private static final String PASSWORD = "manikandan";

	public static Connection getConnection() throws DBException {

		Connection con = null;

		try {
			Class.forName(DRIVERCLASSNAME);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (ClassNotFoundException e) {
			throw new DBException(MessageConstant.DRIVER_CLASS, e);
		} catch (SQLException e) {
			throw new DBException(MessageConstant.SQL_CONNECTION, e);
		}

		return con;
	}

	public static void close(Connection con, PreparedStatement pst, ResultSet rs) throws DBException {

		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			throw new DBException(MessageConstant.CLOSE_CONNECTION, e);
		}
	}

	public static void close(Connection con, PreparedStatement pst) throws DBException {

		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			throw new DBException(MessageConstant.CLOSE_CONNECTION, e);
		}

	}

}
