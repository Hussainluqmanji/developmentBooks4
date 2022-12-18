package com.tcs.developmentbooks4.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.developmentbooks4.enums.BooksEnum;
import com.tcs.developmentbooks4.model.BookRequest;
import com.tcs.developmentbooks4.model.Books;
import com.tcs.developmentbooks4.model.PriceSummary;

@Service
public class BookService {

	private static final double SINGLE_BOOK_PRICE = 50.0;

	public List<Books> getAllBooks() {
		return Arrays.stream(BooksEnum.values()).map(bookEnum -> new Books(bookEnum.getId(), bookEnum.getTitle(),
				bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
	}

	public PriceSummary calulateBooksPriceWithDiscount(List<BookRequest> books) {
		int totalBooks = books.stream().mapToInt(book -> book.getQuantity()).sum();
		int typesOfBook = books.size();
		double actualCost = totalBooks * SINGLE_BOOK_PRICE;
		double discount = 0;
		if (totalBooks == 2 && typesOfBook == 2) {
			discount = 5;
		}
		double finalPrice = actualCost - (actualCost * (discount / 100));
		PriceSummary priceSummary = new PriceSummary();
		priceSummary.setActualPrice(50 * totalBooks);
		priceSummary.setFinalPrice(finalPrice);
		priceSummary.setTotalBooks(totalBooks);
		priceSummary.setTotalDiscount(priceSummary.getActualPrice() - priceSummary.getFinalPrice());
		return priceSummary;
	}
}
