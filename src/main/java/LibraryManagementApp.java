//package com.example.library;

import java.util.List;
import java.util.Scanner;

import com.elibrary.controllers.LibraryController;
import com.elibrary.models.Book;

public class LibraryManagementApp {
	private static LibraryController libraryController;

	public static void main(String[] args) {
		libraryController = new LibraryController();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Library Management System");
			System.out.println("1. Add Book");
			System.out.println("2. Issue Book");
			System.out.println("3. Search Books");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				addBook(scanner);
				break;
			case 2:
				issueBook(scanner);
				break;
			case 3:
				searchBooks(scanner);
				break;
			case 4:
				System.out.println("Exiting...");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void addBook(Scanner scanner) {
		System.out.println("Enter book details:");
		Book book = new Book();

		System.out.print("ID: ");
		book.setId(scanner.nextInt());

		scanner.nextLine(); // Consume newline

		System.out.print("Title: ");
		book.setTitle(scanner.nextLine());

		System.out.print("Author: ");
		book.setAuthor(scanner.nextLine());

		System.out.print("ISBN: ");
		book.setIsbn(scanner.nextLine());

		try {
			libraryController.addBook(book);
			System.out.println("Book added successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Failed to add book: " + e.getMessage());
		}
	}

	private static void issueBook(Scanner scanner) {
		System.out.print("Enter Book ID: ");
		int bookId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		System.out.print("Issued To: ");
		String issuedTo = scanner.nextLine();

		System.out.print("Issued Date: ");
		String issuedDate = scanner.nextLine();

		System.out.print("Due Date: ");
		String dueDate = scanner.nextLine();

		try {
			libraryController.issueBook(bookId, issuedTo, issuedDate, dueDate);
			System.out.println("Book issued successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Failed to issue book: " + e.getMessage());
		}
	}

	private static void searchBooks(Scanner scanner) {
		System.out.print("Enter keyword to search: ");
		String keyword = scanner.nextLine();
		List<Book> books = libraryController.searchBooks(keyword);

		if (books.isEmpty()) {
			System.out.println("No books found matching the keyword: " + keyword);
		} else {
			System.out.println("Books matching the keyword: " + keyword);
			for (Book book : books) {
				System.out.println(book);
			}
		}
	}
}
