package com.revature.gradingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.gradingapp.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>{

	@Query("from Grade g where g.studentDetail.regNo = ?1")
	Grade findByRegNo(int regno);

	@Query("from Grade g where g.grade = ?1")
	List<Grade> findByGrade(String grade);

	@Query(value="select g.* from student_grade g order by g.average desc", nativeQuery = true)
	List<Grade> findTopToBottomGrade();

//	@Query("select g.grade from Grade g where g.grade = ?1")
//	String findGrade(String grade);
	
}
