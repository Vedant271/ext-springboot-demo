package com.example.flipkart.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Builder
public class APIError {

	String fieldName; // colname 37.5 sname : Raj
	Object rejectedValue; // LocalDate, String, Double
	String errorMessage;

	public APIError(String fieldName, Object rejectedValue, String errorMessage) {
		super();
		this.fieldName = fieldName;
		this.rejectedValue = rejectedValue;
		this.errorMessage = errorMessage;
	}
}
