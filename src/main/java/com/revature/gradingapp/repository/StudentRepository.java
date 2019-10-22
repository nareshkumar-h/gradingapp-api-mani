package com.revature.gradingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.gradingapp.model.StudentDetail;

public interface StudentRepository extends JpaRepository<StudentDetail, Integer>{

	@Query("from StudentDetail sd where sd.regNo = :regno")
	StudentDetail findByRegNo(@Param("regno")int regno);
}
