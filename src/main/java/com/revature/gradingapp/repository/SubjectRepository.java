package com.revature.gradingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.gradingapp.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	@Query("from Subject sd where sd.code = :sub_code")
	Subject findBySubCode(@Param("sub_code")String sub_code);
}
