package com.tcs.developmentbooks4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.developmentbooks4.model.BookRequest;
import com.tcs.developmentbooks4.model.Books;
import com.tcs.developmentbooks4.model.PriceSummary;
import com.tcs.developmentbooks4.service.BookService;

@RestController
public class DevelopmentBooksController {

	@Autowired
	BookService service;

	@GetMapping("/getAllBooks")
	public List<Books> getAllBooks() {
		return service.getAllBooks();
	}

	@PostMapping("/buyBooks")
	public PriceSummary buyBooks(@RequestBody List<BookRequest> booksRequest) {
		return service.calulateBooksPriceWithDiscount(booksRequest);
	}
}
