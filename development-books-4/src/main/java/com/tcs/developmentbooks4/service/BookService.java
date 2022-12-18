package com.tcs.developmentbooks4.service;

import java.util.ArrayList;
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
		List<Integer> bookGroups = new ArrayList<Integer>();
		double priceOfSimilarBooksLeft = 0;
		int noOfGroups = 1 + (totalBooks / books.size());
		double finalPrice = 0;

		for (int i = 0; i < noOfGroups; i++) {
			int typesOfBookLeft = (int) books.stream().filter(book -> book.getQuantity() > 0).count();
			if (typesOfBookLeft > 1) {
				bookGroups.add(typesOfBookLeft);
				reduceQuantityOfAlreadyBookIntoGroups(books);
			} else {
				priceOfSimilarBooksLeft = books.stream().filter(book -> book.getQuantity() > 0)
						.mapToDouble(book -> book.getQuantity() * SINGLE_BOOK_PRICE).sum();
				break;
			}
		}

		finalPrice = priceOfSimilarBooksLeft
				+ bookGroups.stream().mapToDouble(group -> calculateDiscountByNoOfTypesOfBooks(group)).sum();
		return createPriceSummary(totalBooks, finalPrice);
	}

	public PriceSummary createPriceSummary(int totalBooks, double finalPrice) {
		PriceSummary priceSummary = new PriceSummary();
		priceSummary.setActualPrice(50 * totalBooks);
		priceSummary.setFinalPrice(finalPrice);
		priceSummary.setTotalBooks(totalBooks);
		priceSummary.setTotalDiscount(priceSummary.getActualPrice() - priceSummary.getFinalPrice());
		return priceSummary;
	}

	public double calculateDiscountByNoOfTypesOfBooks(int groupSize) {
		double discountedPrice = 0;
		double actualCost = groupSize * SINGLE_BOOK_PRICE;
		if (groupSize == 1)
			discountedPrice = 50;
		else if (groupSize == 2)
			discountedPrice = actualCost - (actualCost * (5.0 / 100));
		else if (groupSize == 3)
			discountedPrice = actualCost - (actualCost * (10.0 / 100));
		else if (groupSize == 4)
			discountedPrice = actualCost - (actualCost * (20.0 / 100));
		else if (groupSize == 5)
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

	public void reduceQuantityOfAlreadyBookIntoGroups(List<BookRequest> books) {
		books.forEach(book -> {
			book.setQuantity(book.getQuantity() - 1);
		});
	}
}
