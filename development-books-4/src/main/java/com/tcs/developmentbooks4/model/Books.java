package com.tcs.developmentbooks4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Books {

	private int id;

	private String title;

	private String author;

	private int year;

	private double price;
}
