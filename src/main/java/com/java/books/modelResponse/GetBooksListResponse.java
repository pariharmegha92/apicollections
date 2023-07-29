package com.java.books.modelResponse;

import com.java.books.entity.BookDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBooksListResponse {
    private boolean result;
    private String message;
    private List<BookDetails> data;
}
