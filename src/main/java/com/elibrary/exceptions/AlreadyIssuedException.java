package com.elibrary.exceptions;

public class AlreadyIssuedException extends RuntimeException {
	public AlreadyIssuedException(String message) {
		super(message);
	}
}
