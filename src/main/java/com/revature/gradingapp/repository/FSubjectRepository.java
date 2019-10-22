package com.revature.gradingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.gradingapp.model.Fsubject;

@Repository
public interface FSubjectRepository extends JpaRepository<Fsubject, Integer>{

	@Query("from Fsubject f where f.userDetails.id= ?1 and f.subject.id= ?2")
	Fsubject findByUserIdAndSubId(int userId, int subjectId);

}
