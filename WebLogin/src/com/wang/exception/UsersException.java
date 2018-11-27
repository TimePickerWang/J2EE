package com.wang.exception;

public class UsersException extends Exception {
	public UsersException() {
		super();
	}

	public UsersException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsersException(String message) {
		super(message);
	}

	public UsersException(Throwable cause) {
		super(cause);
	}

}
