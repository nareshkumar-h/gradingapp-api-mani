package com.mani.gradingappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mani.gradingappapi.model.UserDetails;

@Repository
public interface AdminRepository extends JpaRepository<UserDetails, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE UserDetails ud SET ud.name = ?1, ud.mobno = ?2, ud.password = ?3, ud.role = ?4 WHERE ud.email = ?5 ")
	void saveByEmail(String name, long mobno, String password, String role, String email);
	
}
