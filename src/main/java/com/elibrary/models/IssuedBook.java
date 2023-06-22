package com.elibrary.models;

public class IssuedBook {
	private int id;
	private int bookId;
	private String issuedTo;
	private String issuedDate;
	private String dueDate;

	public IssuedBook() {
		super();
	}

	public IssuedBook(int id, int bookId, String issuedTo, String issuedDate, String dueDate) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.issuedTo = issuedTo;
		this.issuedDate = issuedDate;
		this.dueDate = dueDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getIssuedTo() {
		return issuedTo;
	}

	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
