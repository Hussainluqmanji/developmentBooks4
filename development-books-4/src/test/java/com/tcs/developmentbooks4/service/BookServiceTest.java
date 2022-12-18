package com.tcs.developmentbooks4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tcs.developmentbooks4.enums.BooksEnum;
import com.tcs.developmentbooks4.model.Books;

class BookServiceTest {

	BookService service;

	@BeforeEach
	public void setUp() {
		service = new BookService();
	}

	@Test
	public void getAllBooksShouldReturnFiveBookNames() {
		List<Books> books = service.getAllBooks();
		assertEquals(5, books.size());
	}

	@Test
	public void getAllBooksShouldReturnBooksWithDetails() {
		List<Books> result = service.getAllBooks();
		assertEquals(BooksEnum.CLEAN_CODE.getTitle(), result.get(0).getTitle());
		assertEquals(BooksEnum.CLEAN_CODE.getAuthor(), result.get(0).getAuthor());
		assertEquals(BooksEnum.CLEAN_CODE.getId(), result.get(0).getId());
		assertEquals(BooksEnum.CLEAN_CODE.getPrice(), result.get(0).getPrice(), 0.0);
		assertEquals(BooksEnum.CLEAN_CODE.getYear(), result.get(0).getYear());
	}

}
