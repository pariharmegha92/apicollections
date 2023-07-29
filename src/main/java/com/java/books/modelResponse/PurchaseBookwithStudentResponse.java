package com.java.books.modelResponse;

import com.java.books.entity.StudentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PurchaseBookwithStudentResponse {
    private String bookname;
    private int noOfBookPurchase;
    private List<StudentDetails> studentDetails;
}
