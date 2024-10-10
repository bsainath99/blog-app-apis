package com.sainath.blog_app_apis.payloads;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	private Integer categoryId;
	private String categoryTitle;
	private String categoryDescription;
}
