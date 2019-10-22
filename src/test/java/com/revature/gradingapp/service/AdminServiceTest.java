package com.revature.gradingapp.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.gradingapp.exception.ServiceException;
import com.revature.gradingapp.model.ScoreRange;
import com.revature.gradingapp.model.UserDetails;
import com.revature.gradingapp.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	AdminService adminService;

	/** 
	 * adminLogin in AdminService
	 * @Param name and password
	 * return UserDetails Object
	 */
	@Test
	public void adminLoginTest() {
		
		String name = "Admin";
		String pwd = "Admin123";
		
		UserDetails userDetails = null;
		try {
			userDetails = adminService.adminLogin(name, pwd);
		} catch (ServiceException e) {
			LOGGER.debug(e.getMessage());
		}
		assertNotNull(userDetails);
	}

	/** 
	 * viewScoreRangeService in AdminService
	 * @Param No
	 * return List<ScoreRange>
	 */
	@Test
	public void viewScoreRangeServiceTest() {
		
		List<ScoreRange> list = null;
		try {
			list = adminService.viewScoreRangeService();
		} catch (ServiceException e) {
			LOGGER.debug(e.getMessage());
		}
		assertNotNull(list);
	}
}
