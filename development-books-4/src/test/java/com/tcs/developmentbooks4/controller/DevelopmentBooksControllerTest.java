package com.tcs.developmentbooks4.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tcs.developmentbooks4.model.BookRequest;
import com.tcs.developmentbooks4.model.Books;
import com.tcs.developmentbooks4.service.BookService;

class DevelopmentBooksControllerTest {

	DevelopmentBooksController controller;

	@BeforeEach
	public void setUp() {
		controller = new DevelopmentBooksController();
		controller.service = new BookService();
	}

	@Test
	void getBookNameShouldReturnNameOfTheBook() {
		List<Books> result = controller.getAllBooks();
		assertEquals("Clean Code", result.get(0).getTitle());
	}

	@Test
	public void getAllBooksShouldReturnFiveBookNames() {
		List<Books> books = controller.getAllBooks();
		assertEquals(5, books.size());
	}

	@Test
	public void buyBookShouldReturnSuccessMessage() {
		String result = controller.buyBook(new BookRequest(1, 1));
		assertEquals("Book bought..", result);
	}

}
