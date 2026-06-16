package com.example.flipkart.dto;

import com.example.flipkart.util.ProductCategory;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Builder
public class ProductDTO {
	public int id;
	public String title;
	public double price;
	public String description;
//	@NotNull(message = "Category is required")
//	public ProductCategory category;
	public String category;
	public String image;
	public RatingDTO rating;
}
