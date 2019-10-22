package com.revature.gradingapp.util;

import lombok.Data;

@Data
public class Message {

	private String errorMessage;
	
	private String infoMessage;

	public Message(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public Message() {
	}
}
