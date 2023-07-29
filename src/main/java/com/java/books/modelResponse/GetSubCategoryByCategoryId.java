package com.java.books.modelResponse;

import com.java.books.entity.SubCategoryDetails;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSubCategoryByCategoryId {
    private boolean result;
    private String message;
    private String categoryName;
    private int noOfSubcategory;
    private List<SubCategoryDetails> data;
}
