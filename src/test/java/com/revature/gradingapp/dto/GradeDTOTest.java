package com.revature.gradingapp.dto;

import static org.junit.Assert.*;

import org.junit.Test;

public class GradeDTOTest {

	public GradeDTO crateTestSuite(){
	      return new GradeDTO();
	     }

	    @Test
	    public void testGetGrade() {
	     String grade= "A";
	     GradeDTO gradeDTO =null;
	     gradeDTO = crateTestSuite();
	     gradeDTO.setGrade(grade);
	     
	     String getGrade = gradeDTO.getGrade();
	     
	     assertEquals("A", getGrade);
	    }
	    
	    @Test
	    public void testGetMin() {
	     long min= 90L;
	     int testMin = (int)min;
	     GradeDTO gradeDTO =null;
	     gradeDTO = crateTestSuite();
	     gradeDTO.setMin(testMin);
	     
	     Integer getmin = gradeDTO.getMin();
	     long test = (long)getmin;
	     
	     assertEquals(min, test);
	    }
	    
	    @Test
	    public void testGetMax() {
	     long max= 90L;
		 int testMax = (int)max;
	     GradeDTO gradeDTO =null;
	     gradeDTO = crateTestSuite();
	     gradeDTO.setMax(testMax);
	     
	     Integer getmax = gradeDTO.getMax();
	     long test = (long)getmax;
	     
	     assertEquals(max, test);
	    }

//	    @Test
//	    public void testSetGrade() {
//	     String grade1= "A";
//	     GradeDTO gradeDTO =null;
//	     gradeDTO = crateTestSuite();
//	     gradeDTO.setGrade(grade);
//	    }

}
