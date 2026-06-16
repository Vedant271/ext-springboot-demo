package com.example.flipkart.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductCategory {
	MENSCLOTHING("men's clothing"),
	JEWELERY("jewelery"),
	ELECTRONICS("electronics"),
	WOMENCLOTHING("women's clothing");
	
	private final String category;

	private ProductCategory(String category) {
		this.category = category;
	}

    @JsonValue     		 
    public String getCategory() { // to client, from enum to response body, enum -> String
        return category;
    }

    @JsonCreator
    public static ProductCategory fromValue(String value) { // from client, from request body to enum, String -> enum

        for (ProductCategory category : ProductCategory.values()) {
            if (category.category.equalsIgnoreCase(value)) {
                return category;
            }
        }

        throw new IllegalArgumentException("Invalid category: " + value);
    }
}
