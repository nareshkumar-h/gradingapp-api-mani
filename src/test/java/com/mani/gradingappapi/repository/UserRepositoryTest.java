package com.mani.gradingappapi.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mani.gradingappapi.model.UserDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	/** 
	 * login in UserRepository
	 * @Param name, password and role
	 * return UserDetails Object
	 */
	@Test
	public void loginTest() {
		
		String name = "Admin";
		String password = "Admin123";
		String role = "A";
		
		UserDetails userDetails = userRepository.login(name, password, role);
		assertNotNull(userDetails);
	}
	
	/** 
	 * findByRole in UserRepository
	 * @Param role
	 * return List of UserDetails Object
	 */
	@Test
	public void findByRoleTest() {

		String role = "A";
		List<UserDetails> userDetails = userRepository.findByRole( role );
		assertNotNull(userDetails);
	}
	
	/** 
	 * findByEmail in UserRepository
	 * @Param email
	 * return UserDetails Object
	 */
	@Test
	public void findByEmailTest() {
		
		String email = "admin@gmail.com";
		UserDetails userDetails = userRepository.findByEmail(email);
		assertNotNull(userDetails);
	}
	
	/** 
	 * findByMobNo in UserRepository
	 * @Param mobileNo
	 * return UserDetails Object
	 */
	@Test
	public void findByMobNoTest() {
		
		Long mobileNo = 1234567891L;
		UserDetails userDetails = userRepository.findByMobNo(mobileNo);
		assertNotNull(userDetails);
	}
}
