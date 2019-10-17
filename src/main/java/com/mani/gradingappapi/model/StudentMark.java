package com.mani.gradingappapi.model;

import java.io.Serializable;

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
@Table(name = "student_marks")
public class StudentMark implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subject_code",referencedColumnName = "sub_code")
	private Subject subject;
	
	@Column(name="marks")
	private Integer mark;
	
	@ManyToOne(fetch = FetchType.EAGER)
	
	
	@JoinColumn(name="reg_no", referencedColumnName = "reg_no")
	private StudentDetail studentDetail;
}
