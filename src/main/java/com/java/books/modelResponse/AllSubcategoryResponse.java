package com.java.books.modelResponse;

import com.java.books.entity.SubCategoryDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllSubcategoryResponse {
    private boolean result;
    private String message;
    private SubCategoryDetails data;
}
