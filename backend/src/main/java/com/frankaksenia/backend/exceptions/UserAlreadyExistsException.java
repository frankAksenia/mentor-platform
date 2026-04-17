package com.frankaksenia.backend.exceptions;

import lombok.Getter;

public class UserAlreadyExistsException extends RuntimeException {
    	private static final long serialVersionUID = 1L;
	
    @Getter
	private final String exceptionName;
	
	public UserAlreadyExistsException(String exceptionName, String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionName = exceptionName;
	}
}
