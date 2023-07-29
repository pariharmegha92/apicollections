package com.java.books.modelResponse;

import com.java.books.entity.BookDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBookResponse {
    private boolean result;
    private String message;
    private BookDetails data;
}
