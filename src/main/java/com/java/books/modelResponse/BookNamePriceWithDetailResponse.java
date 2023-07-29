package com.java.books.modelResponse;

import com.java.books.entity.BookDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookNamePriceWithDetailResponse {
    private String bookName;
    private String price;
    private BookDetails bookDetails;
}
