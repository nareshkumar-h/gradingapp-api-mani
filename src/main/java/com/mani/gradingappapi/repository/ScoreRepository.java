package com.mani.gradingappapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mani.gradingappapi.model.ScoreRange;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreRange, String>{

	@Query(value="select get_grade(:avg)", nativeQuery = true)
	String findByAverage(@Param("avg")float avg); 
	
}
