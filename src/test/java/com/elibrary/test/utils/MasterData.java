package com.elibrary.test.utils;

import java.util.ArrayList;
import java.util.List;

import com.elibrary.models.Book;
import com.elibrary.models.IssuedBook;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {
	public static Book getBookData() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Java");
		book.setAuthor("ABC");
		book.setIsbn("1234567890");
		return book;
	}

	public static List<Book> getBookList() {
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setTitle("Java");
		book.setAuthor("ABC");
		book.setIsbn("1234567890");

		books.add(book);

		book = new Book();
		book.setTitle("Java 2");
		book.setAuthor("ABC 2");
		book.setIsbn("1234567891");

		books.add(book);

		return books;
	}

	public static IssuedBook getBookIssueData() {
		IssuedBook bookIssue = new IssuedBook();
		bookIssue.setId(1);
		bookIssue.setBookId(1);
		bookIssue.setDueDate("2023-11-10");
		bookIssue.setIssuedDate("2023-06-22");
		bookIssue.setIssuedTo("ABC");
		return bookIssue;
	}

	public static List<IssuedBook> getBookIssueList() {
		List<IssuedBook> bookIssues = new ArrayList<>();

		IssuedBook bookIssue = new IssuedBook();
		bookIssue.setId(1);
		bookIssue.setBookId(1);
		bookIssue.setDueDate("2023-11-10");
		bookIssue.setIssuedDate("2023-06-22");
		bookIssue.setIssuedTo("ABC");

		bookIssues.add(bookIssue);

		bookIssue = new IssuedBook();
		bookIssue.setId(2);
		bookIssue.setBookId(2);
		bookIssue.setDueDate("2023-06-29");
		bookIssue.setIssuedDate("2023-06-10");
		bookIssue.setIssuedTo("ABC 2");

		bookIssues.add(bookIssue);

		return bookIssues;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
