package com.java.books.modelResponse;

import com.java.books.entity.BranchDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBranchResponse {
    private boolean result;
    private String message;
    private BranchDetails Data;
}
