package com.tcs.developmentbooks4.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.developmentbooks4.enums.BooksEnum;
import com.tcs.developmentbooks4.model.BookRequest;
import com.tcs.developmentbooks4.model.Books;

@Service
public class BookService {

	public List<Books> getAllBooks() {
		return Arrays.stream(BooksEnum.values()).map(bookEnum -> new Books(bookEnum.getId(), bookEnum.getTitle(),
				bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
	}

	public double buyBook(BookRequest bookRequest) {
		List<Books> books = getAllBooks();
		return books.stream().filter(book -> book.getId() == bookRequest.getBookId()).findAny().get().getPrice();
	}
}
