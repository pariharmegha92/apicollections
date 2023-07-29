package com.java.books.modelResponse;

import com.java.books.entity.BookDetails;
import com.java.books.entity.StudentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class StudentDetailsWithBook {
    private String studentName;
    private String emailId;
    private int noOfBookPurchase;
    private List<BookDetails> bookDetails;
}
