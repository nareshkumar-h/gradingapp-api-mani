package com.mani.gradingappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mani.gradingappapi.dto.StudentGradeDTO;
import com.mani.gradingappapi.exception.DBException;
import com.mani.gradingappapi.model.StudentMark;
import com.mani.gradingappapi.util.ConnectionUtil;
import com.mani.gradingappapi.util.MessageConstant;

public class StudentGradeDaoImpl {
	final String AVERAGE = "average";

	public void insertGrade(int regno, List<StudentMark> marks) throws DBException {

		int total = 0;
		for (StudentMark studentMark : marks) {
			total = total + studentMark.getMark();
		}

		float avg = (float) total / marks.size();
		String grade = findGrade(avg);

		// insert grade into student_grade
		Connection con = null;
		PreparedStatement pst = null;
		con = ConnectionUtil.getConnection();

		try {
			String sql = "insert into student_grade ( reg_no, average, grade ) values (?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);
			pst.setFloat(2, avg);
			pst.setString(3, grade);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_INSERT_GRADE, e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

	// GradeDAO
	public String findGrade(float avg) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String grade = "";

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade from score_range where " + avg + " between min and max ";
			pst = con.prepareStatement(sql);
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

	public List<StudentGradeDTO> findByGrade(String grade) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentGradeDTO> list = new ArrayList<StudentGradeDTO>();
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select sd.reg_no, sd.student_name, g.average, g.grade from student_details sd, student_grade g where sd.reg_no = g.reg_no and grade = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, grade);

			rs = pst.executeQuery();
			while (rs.next()) {
				StudentGradeDTO sg = new StudentGradeDTO();
				sg.setRegNo(rs.getInt("reg_no"));
				sg.setStudentName(rs.getString("student_name"));
				sg.setAvg(rs.getInt(AVERAGE));
				sg.setGrade(rs.getString("grade"));

				list.add(sg);
			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_RECORDS, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}

		return list;
	}

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

	public StudentGradeDTO findByRegNo(int regno) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StudentGradeDTO stud = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select sd.student_name, sd.reg_no, sg.average, sg.grade from student_details sd,"
					+ " student_grade sg where sd.reg_no = sg.reg_no and sd.reg_no = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);

			rs = pst.executeQuery();
			if (rs.next()) {
				stud = new StudentGradeDTO();
				stud.setStudentName(rs.getString("student_name"));
				stud.setRegNo(rs.getInt("reg_no"));
				stud.setAvg(rs.getFloat(AVERAGE));
				stud.setGrade(rs.getString("grade"));

			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_RECORDS, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return stud;
	}

	public List<StudentGradeDTO> listOfStudent() throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentGradeDTO> stud = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select sd.student_name, sd.reg_no, sg.average, sg.grade from student_details sd, student_grade sg where sd.reg_no = sg.reg_no order by average desc";
			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();
			stud = new ArrayList<StudentGradeDTO>();
			while (rs.next()) {
				StudentGradeDTO studGrade = new StudentGradeDTO();
				studGrade.setStudentName(rs.getString("student_name"));
				studGrade.setRegNo(rs.getInt("reg_no"));
				studGrade.setAvg(rs.getFloat(AVERAGE));
				studGrade.setGrade(rs.getString("grade"));

				stud.add(studGrade);
			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_RECORDS, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return stud;
	}

}
