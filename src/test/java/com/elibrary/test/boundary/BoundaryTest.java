package com.elibrary.test.boundary;

import static com.elibrary.test.utils.TestUtils.boundaryTestFile;
import static com.elibrary.test.utils.TestUtils.currentTest;
import static com.elibrary.test.utils.TestUtils.testReport;
import static com.elibrary.test.utils.TestUtils.yakshaAssert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.elibrary.controllers.LibraryController;
import com.elibrary.exceptions.DuplicateBookException;
import com.elibrary.models.Book;
import com.elibrary.models.IssuedBook;
import com.elibrary.test.utils.MasterData;

public class BoundaryTest {

	static LibraryController bookInventory = null;

	@BeforeAll
	public static void setUp() {
		bookInventory = new LibraryController();
		bookInventory.books = MasterData.getBookList();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testBookIsbnIsUnique() throws Exception {
		Book book = MasterData.getBookData();
		book.setIsbn("1234567899");
		try {
			bookInventory.addBook(book);
			yakshaAssert(currentTest(), true, boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}

	}
//
//
//	@Test
//	public void testBookIssueIsbnAlreadyExists() throws Exception {
//		List<Book> books = bookInventory.showInventory();
//		BookIssue bookIssue = MasterData.getBookIssueData();
//		List<String> isbns = new ArrayList<>();
//		try {
//			for (Book book : books) {
//				isbns.add(book.getIsbn());
//			}
//		} catch (Exception e) {
//		}
//
//		yakshaAssert(currentTest(), isbns.contains(bookIssue.getIsbn()) ? true : false, boundaryTestFile);
//	}

//	@Test
//	public void testBookIssueDateIsPastOrPresent() throws Exception {
//		BookIssue bookIssue = MasterData.getBookIssueData();
//
//		yakshaAssert(currentTest(),
//				bookIssue.getIssueDate().isBefore(LocalDate.now()) || bookIssue.getIssueDate().isEqual(LocalDate.now())
//						? true
//						: false,
//				boundaryTestFile);
//	}

}