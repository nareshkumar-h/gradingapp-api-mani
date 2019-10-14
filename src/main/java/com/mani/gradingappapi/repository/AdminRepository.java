package com.mani.gradingappapi.repository;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mani.gradingappapi.model.ScoreRange;
import com.mani.gradingappapi.model.UserDetails;

@Repository
public interface AdminRepository extends JpaRepository<UserDetails, Integer>{

	@Query("insert into UserDetails(name, email, mobile, password, role, subject ) values ( :name, :email, :mob_no, :password, :role, :subject)")
	public abstract void insertEmployee(@Param("") UserDetails user);
	
	@Query("insert into ScoreRange (grade, min, max) values (:grade, :min, :min)")
	public abstract void defineScore(@Param("grade")String grade, @Param("min")int min, @Param("max")int max );
	
	@Query("from ScoreRange")
	public abstract List<ScoreRange> viewScore();
	
	
}
