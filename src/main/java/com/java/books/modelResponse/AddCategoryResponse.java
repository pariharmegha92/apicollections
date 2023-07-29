package com.java.books.modelResponse;

import com.java.books.entity.CategoryDetails;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AddCategoryResponse {
    private boolean result;
    private String message;
    private CategoryDetails data;
}
