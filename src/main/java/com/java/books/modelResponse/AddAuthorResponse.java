package com.java.books.modelResponse;

import com.java.books.entity.AuthorDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAuthorResponse {
    private boolean result;
    private String message;
    private AuthorDetails data;
}
