package com.example.gateways.currency;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyResponse {
	private String date;
	private double amount;
	private Map<String, Double> rates;
	private String base;
}