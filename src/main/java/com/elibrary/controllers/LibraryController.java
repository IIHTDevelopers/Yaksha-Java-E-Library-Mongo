package com.elibrary.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.elibrary.models.Book;
import com.elibrary.models.IssuedBook;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class LibraryController {
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> bookCollection;
	private MongoCollection<Document> issuedBookCollection;
	private Gson gson;
	public List<Book> books;

	public LibraryController() {
		mongoClient = MongoClients.create(); // Create a MongoDB client
		database = mongoClient.getDatabase("library"); // Get the "library" database
		bookCollection = database.getCollection("books"); // Get the "books" collection
		issuedBookCollection = database.getCollection("issued_books"); // Get the "issued_books" collection
		gson = new Gson(); // Gson instance for JSON serialization/deserialization
	}

	public List<Book> convertMongoCollectionToList(MongoCollection<Document> bookCollection) {
		List<Book> books = new ArrayList<>();
		for (Document document : bookCollection.find()) {
			Book book = new Book();
			book.setId(Integer.parseInt(document.getObjectId("_id").toString()));
			book.setTitle(document.getString("title"));
			book.setAuthor(document.getString("author"));
			books.add(book);
		}
		return books;
	}

	public void addBook(Book book) {
		if (getBookByIsbn(book.getIsbn()) != null) {
			throw new IllegalArgumentException("Book with the same ISBN already exists.");
		}
		Document doc = Document.parse(gson.toJson(book));
		bookCollection.insertOne(doc);
	}

	public void issueBook(int bookId, String issuedTo, String issuedDate, String dueDate) {
		Book book = getBookById(bookId);
		if (book == null) {
			throw new IllegalArgumentException("Book not found with ID: " + bookId);
		}

		if (getIssuedBookByBookId(bookId) != null) {
			throw new IllegalArgumentException("Book is already issued.");
		}

		IssuedBook issuedBook = new IssuedBook();
		issuedBook.setId(generateUniqueId());
		issuedBook.setBookId(bookId);
		issuedBook.setIssuedTo(issuedTo);
		issuedBook.setIssuedDate(issuedDate);
		issuedBook.setDueDate(dueDate);

		Document doc = Document.parse(gson.toJson(issuedBook));
		issuedBookCollection.insertOne(doc);
	}

	public Book getBookById(int bookId) {
		Document doc = bookCollection.find(new Document("id", bookId)).first();
		if (doc != null) {
			return gson.fromJson(doc.toJson(), Book.class);
		}
		return null;
	}

	public Book getBookByIsbn(String isbn) {
		Document doc = bookCollection.find(new Document("isbn", isbn)).first();
		if (doc != null) {
			return gson.fromJson(doc.toJson(), Book.class);
		}
		return null;
	}

	public IssuedBook getIssuedBookByBookId(int bookId) {
		Document doc = issuedBookCollection.find(new Document("bookId", bookId)).first();
		if (doc != null) {
			return gson.fromJson(doc.toJson(), IssuedBook.class);
		}
		return null;
	}

	public List<Book> searchBooks(String keyword) {
		Document filter = new Document("$or", List.of(new Document("title", new Document("$regex", keyword)),
				new Document("author", new Document("$regex", keyword))));

		List<Book> books = new ArrayList<>();
		for (Document doc : bookCollection.find(filter)) {
			books.add(gson.fromJson(doc.toJson(), Book.class));
		}

		return books;
	}

	private int generateUniqueId() {
		// You can use any logic to generate a unique ID, such as an auto-incrementing
		// counter or UUID.
		// For simplicity, let's use a random number for now.
		return (int) (Math.random() * 100000);
	}
}
