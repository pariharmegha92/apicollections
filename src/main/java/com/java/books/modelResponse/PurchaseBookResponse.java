package com.java.books.modelResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseBookResponse {
    private boolean result;
    private String message;
    private List<StudentDetailsWithBook> studentDetailsWithBooks;
}
