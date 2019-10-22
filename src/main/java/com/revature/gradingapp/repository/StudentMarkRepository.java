package com.revature.gradingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.gradingapp.model.StudentMark;

public interface StudentMarkRepository extends JpaRepository<StudentMark, Integer> {

	@Query(value="select sm.* from student_marks sm where sm.subject_code =:code order by sm.marks desc", nativeQuery = true)
	List<StudentMark> findBySubjectCode(@Param("code") String subCode);

	@Query(" from StudentMark sm where sm.studentDetail.regNo =?1")
	List<StudentMark> findByRegNo(int regno);

}
