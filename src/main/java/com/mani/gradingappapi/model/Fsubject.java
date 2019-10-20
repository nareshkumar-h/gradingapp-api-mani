package com.mani.gradingappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="faculty_subject")
public class Fsubject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "user_id", referencedColumnName = "id")
	private UserDetails userDetails;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "subject_id", referencedColumnName = "id")
	private Subject subject;
}
