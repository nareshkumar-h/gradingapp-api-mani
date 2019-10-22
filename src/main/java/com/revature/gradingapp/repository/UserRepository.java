package com.revature.gradingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.gradingapp.model.UserDetails;


@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>{
	
	@Query(" from UserDetails where name = :name and password = :password and role = :role")
	UserDetails login(@Param("name") String name, @Param("password") String password, @Param("role") String role);

	@Query(" from UserDetails where role = :role")
	List<UserDetails> findByRole(@Param("role") String role);

	@Query(" from UserDetails where email = ?1 ")
	UserDetails findByEmail(String email);

	@Query(" from UserDetails where mobno = ?1 ")
	UserDetails findByMobNo(Long mobno);
	
	
	
	
}

