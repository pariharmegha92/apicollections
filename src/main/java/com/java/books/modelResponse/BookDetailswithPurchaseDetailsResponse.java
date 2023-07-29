package com.java.books.modelResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookDetailswithPurchaseDetailsResponse {
    private boolean result;
    private String message;
    private List<PurchaseBookwithStudentResponse> purchaseBookwithStudentResponseList;
}
