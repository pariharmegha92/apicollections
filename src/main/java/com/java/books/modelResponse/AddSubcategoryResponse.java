package com.java.books.modelResponse;

import com.java.books.entity.SubCategoryDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddSubcategoryResponse {
    private boolean result;
    private String message;
    private SubCategoryDetails data;
}
