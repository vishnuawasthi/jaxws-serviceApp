package com.app.exception;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4802219859276056819L;

	private String message;

	public String getMessage() {
		return message;
	}

	public UserNotFoundException(String message) {
		this.message = message;
	}
	
	

}
