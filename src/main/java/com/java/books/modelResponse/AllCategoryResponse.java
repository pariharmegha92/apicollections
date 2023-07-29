package com.java.books.modelResponse;

import com.java.books.entity.CategoryDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllCategoryResponse {
    private boolean result;
    private String message;
    private List<CategoryDetails> data;
}
