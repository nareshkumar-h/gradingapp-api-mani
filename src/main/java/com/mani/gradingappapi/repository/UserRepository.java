package com.mani.gradingappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mani.gradingappapi.model.UserDetails;


@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>{

	
	@Query(" from UserDetails where name = :name and password = :password and role = :role")
	UserDetails login(@Param("name") String name, @Param("password") String password, @Param("role") String role);
	
	
	
	
}

