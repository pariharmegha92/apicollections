package com.java.books.modelResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksResponseWithName {
    private boolean result;
    private String message;
    private BookNamePriceWithDetailResponse[] books;
//    private List<BookNamePriceWithDetailResponse> books;
}
