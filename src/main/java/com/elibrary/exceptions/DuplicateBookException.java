package com.elibrary.exceptions;

public class DuplicateBookException extends RuntimeException {
	public DuplicateBookException(String message) {
		super(message);
	}
}
