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
		if (books.size() == 1)
			return createPriceSummaryForOnlyOneBookType(books.get(0));
		int totalBooks = books.stream().mapToInt(book -> book.getQuantity()).sum();
		int typesOfBook = books.size();
		return createPriceSummary(totalBooks, calculateDiscountByNoOfTypesOfBooks(totalBooks, typesOfBook));
	}

	public PriceSummary createPriceSummary(int totalBooks, double finalPrice) {
		PriceSummary priceSummary = new PriceSummary();
		priceSummary.setActualPrice(50 * totalBooks);
		priceSummary.setFinalPrice(finalPrice);
		priceSummary.setTotalBooks(totalBooks);
		priceSummary.setTotalDiscount(priceSummary.getActualPrice() - priceSummary.getFinalPrice());
		return priceSummary;
	}

	public double calculateDiscountByNoOfTypesOfBooks(int totalBooks, int typesOfBook) {
		double discountedPrice = 0;
		double actualCost = totalBooks * SINGLE_BOOK_PRICE;
		if (totalBooks == 1 && typesOfBook == 1)
			discountedPrice = 50;
		else if (totalBooks == 2 && typesOfBook == 2)
			discountedPrice = actualCost - (actualCost * (5.0 / 100));
		else if (totalBooks == 3 && typesOfBook == 3)
			discountedPrice = actualCost - (actualCost * (10.0 / 100));
		else if (totalBooks == 4 && typesOfBook == 4)
			discountedPrice = actualCost - (actualCost * (20.0 / 100));
		else if (totalBooks == 5 && typesOfBook == 5)
			discountedPrice = actualCost - (actualCost * (25.0 / 100));
		else
			discountedPrice = actualCost;
		return discountedPrice;
	}
	
    public PriceSummary createPriceSummaryForOnlyOneBookType(BookRequest booksInput) {
        PriceSummary priceSummary = new PriceSummary();
        priceSummary.setActualPrice(50 * booksInput.getQuantity());
        priceSummary.setFinalPrice(50 * booksInput.getQuantity());
        priceSummary.setTotalBooks(booksInput.getQuantity());
        priceSummary.setTotalDiscount(0);
        return priceSummary;
    }
}
