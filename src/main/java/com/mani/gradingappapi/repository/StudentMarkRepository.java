package com.mani.gradingappapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mani.gradingappapi.model.StudentMark;

public interface StudentMarkRepository extends JpaRepository<StudentMark, Integer> {

	@Query(" from StudentMark sm where sm.subject.code =:code")
	List<StudentMark> findBySubjectCode(@Param("code") String subCode);

	@Query(" from StudentMark sm where sm.studentDetail.regNo =?1")
	List<StudentMark> findByRegNo(int regno);

}
