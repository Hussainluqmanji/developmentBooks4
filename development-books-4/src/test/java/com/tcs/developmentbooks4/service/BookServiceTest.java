package com.tcs.developmentbooks4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tcs.developmentbooks4.enums.BooksEnum;
import com.tcs.developmentbooks4.model.BookRequest;
import com.tcs.developmentbooks4.model.Books;
import com.tcs.developmentbooks4.model.PriceSummary;

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

	@Test
	public void buyBooksShouldReturnPriceOfBooks() {
		List<BookRequest> books = new ArrayList<BookRequest>();
		books.add(new BookRequest(1, 2));
		PriceSummary result = service.calulateBooksPriceWithDiscount(books);
		assertEquals(100.0, result.getFinalPrice());
	}

	@Test
	public void buyBooksShouldReturnFivePercentDiscountedPriceForTwoDiffBooks() {
		List<BookRequest> books = new ArrayList<BookRequest>();
		books.add(new BookRequest(1, 1));
		books.add(new BookRequest(2, 1));
		PriceSummary result = service.calulateBooksPriceWithDiscount(books);
		assertEquals(95.0, result.getFinalPrice());
	}

	@Test
	public void buyBooksShouldReturnTenPercentDiscountedPriceForThreeDiffBooks() {
		List<BookRequest> books = new ArrayList<BookRequest>();
		books.add(new BookRequest(1, 1));
		books.add(new BookRequest(2, 1));
		books.add(new BookRequest(3, 1));
		PriceSummary result = service.calulateBooksPriceWithDiscount(books);
		assertEquals(135.0, result.getFinalPrice(), 0.0);
	}
	
    @Test
    public void buyFourDiffBookAndGetDiscount() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(2, 1));
        books.add(new BookRequest(3, 1));
        books.add(new BookRequest(4, 1));
        PriceSummary result = service.calulateBooksPriceWithDiscount(books);
        assertEquals(160.0, result.getFinalPrice(), 0.0);
    }
    
    @Test
    public void buyBooksShouldReturnTwentyFivePercentDiscountedPriceForFiveDiffBooks() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(2, 1));
        books.add(new BookRequest(3, 1));
        books.add(new BookRequest(4, 1));
        books.add(new BookRequest(5, 1));
        PriceSummary result = service.calulateBooksPriceWithDiscount(books);
        assertEquals(187.5, result.getFinalPrice());
    }
    
    @Test
	public void buyBooksShouldReturnActualPriceWithoutDiscountedForMultipleBooksOfSameType() {
		List<BookRequest> books = new ArrayList<BookRequest>();
		books.add(new BookRequest(1, 5));
		PriceSummary result = service.calulateBooksPriceWithDiscount(books);
		assertEquals(250.0, result.getFinalPrice());
	}
    
    @Test
    public void buyBooksShouldReturnDiscountedPriceAfterGroupingBooksIntoGroups() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 2));
        books.add(new BookRequest(2, 2));
        books.add(new BookRequest(3, 2));
        books.add(new BookRequest(4, 2));
        books.add(new BookRequest(5, 2));
        PriceSummary result = service.calulateBooksPriceWithDiscount(books);
        assertEquals(375.0, result.getFinalPrice());
    }
    
    @Test
    public void buyBooksShouldReturnDiscountedPriceAfterGroupingBooksIntoGroupsAlongWithSimilarBooksLeftWithNoDiscount() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 2));
        books.add(new BookRequest(2, 2));
        books.add(new BookRequest(3, 2));
        books.add(new BookRequest(4, 2));
        books.add(new BookRequest(5, 12));
        PriceSummary result = service.calulateBooksPriceWithDiscount(books);
        assertEquals(875.0, result.getFinalPrice());

    }
    
    @Test
    public void buyBooksShouldReturnMaximumDiscountedPriceAfterOptimizingGroupingOfBooks() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 2));
        books.add(new BookRequest(2, 2));
        books.add(new BookRequest(3, 2));
        books.add(new BookRequest(4, 1));
        books.add(new BookRequest(5, 1));
        PriceSummary result = service.calulateBooksPriceWithDiscount(books);
        assertEquals(320.0, result.getFinalPrice(), 0.0);
    }

}
