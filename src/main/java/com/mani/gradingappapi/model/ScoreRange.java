package com.mani.gradingappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "score_range")
public class ScoreRange {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name= "grade")
	private String grade;
	
	@Column(name= "min")
	private int min;
	
	@Column(name= "max")
	private int max;
	
}
