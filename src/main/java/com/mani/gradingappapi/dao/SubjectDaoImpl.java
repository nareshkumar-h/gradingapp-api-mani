package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.model.Subject;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;

public class SubjectDaoImpl {

	public List<Subject> findAll() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Subject> list = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id, sub_code, subject_name from subject";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<Subject>();
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setCode(rs.getString("sub_code"));
				sub.setName(rs.getString("subject_name"));
				list.add(sub);

			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_SUBJECTS, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return list;
	}
}
