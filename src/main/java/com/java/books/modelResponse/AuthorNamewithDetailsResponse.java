package com.java.books.modelResponse;

import com.java.books.entity.AuthorDetails;
import com.java.books.entity.BookDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorNamewithDetailsResponse {
    private String authorName;
    private int noOfBooksPublish;
    private List<BookNamePriceWithDetailResponse> books;
//    private List<BookDetails> books;
}
